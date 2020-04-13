package com.revature.g2g.repositories;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomJTDAO implements IPlayerRoomJTDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(PlayerRoomJT pr) {
		
		Session s = sf.getCurrentSession();
		s.save(pr);
		
	}

	@Override
	public PlayerRoomJT findById(int id) {
		
		Session s = sf.getCurrentSession();
		return s.get(PlayerRoomJT.class, id);
		
	}

	@Override
	public int countCurrentPlayers() {
		
		Session ses = this.sf.getCurrentSession();

		String sql = "SELECT COUNT(*) FROM ADMIN.G2G_PLAYER_ROOM_JT WHERE LEFT_ROOM IS NULL";
		
		Query<?> query = ses.createNativeQuery(sql);
		
		try {
			
			BigDecimal total = (BigDecimal) query.uniqueResult();
			return total.intValue();
		
		} catch (HibernateException e) {
			
			return 0;
			
		} 
	
	}

	@Override
	public int countCurrentPlayers(Room room) {
		
		Session ses = sf.getCurrentSession();
		
		int roomID = room.getRoomId();
		
		String sql = "SELECT COUNT(*) FROM G2G_PLAYER_ROOM_JT WHERE LEFT_ROOM IS NULL AND ROOM_ID=" + roomID;
		
		Query<?> query = ses.createNativeQuery(sql);
		
		try {
			
			BigDecimal total = (BigDecimal) query.uniqueResult();
			return total.intValue();
		
		} catch (HibernateException e) {
			
			return 0;
			
		} 
	}

	@Override
	public Set<PlayerRoomJT> findAll() {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		query.from(PlayerRoomJT.class);
		
		Query <PlayerRoomJT> room = ses.createQuery(query);
		
		try {
			
			return room.getResultStream()
					.collect(Collectors.toSet());
			
		} catch(javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public Set<Player> findPlayers(Room room) {
		Set<Player> set = new HashSet<>();
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		Root <PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("room"), room));
		
		Query<PlayerRoomJT> roomJT = ses.createQuery(query);
		
		try {
			
			List<PlayerRoomJT> roomJTList = roomJT.list();
			
			for (int i = 0; i < roomJTList.size(); i++) {
				
				PlayerRoomJT r = roomJTList.get(i);
				Player player = r.getPlayer();
				
				if(player != null) {
					
					set.add(player);
					
				}
			}
			
			return set;
			
		} catch (HibernateException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public Set<Room> findRooms(Player player) {
		Set<Room> set = new HashSet<>();
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		Root <PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("player"), player));
		
		Query<PlayerRoomJT> roomJT = ses.createQuery(query);
		
		try {
			
			List<PlayerRoomJT> roomJTList = roomJT.list();
			
			for (int i = 0; i < roomJTList.size(); i++) {
				
				PlayerRoomJT r = roomJTList.get(i);
				Room room = r.getRoom();
				
				if(room != null) {
					set.add(room);
				}
			}
			
			return set;
			
		} catch (HibernateException e) {
			
			return Collections.emptySet();
			
		}
		
	}
	
	@Override
	public PlayerRoomJT findByPlayerRoom(Player player, Room room) {
		Set<PlayerRoomJT> set = null;
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		Root<PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		Path<Object> playerPath = root.get("player");
		Path<Object> roomPath = root.get("room");
		
		Predicate skillPredicate = builder.equal(playerPath, player);
		Predicate gamePredicate = builder.equal(roomPath, room);
		Predicate skillAndGamePredciate = builder.and(skillPredicate, gamePredicate);
		
		query.select(root).where(skillAndGamePredciate);
		
		Query<PlayerRoomJT> sg = ses.createQuery(query);
		
		try {
			
			set = sg.getResultStream()
					.collect(Collectors.toSet());
			
			if(set.size() == 1) {
				return sg.getSingleResult();
			} else {
				return set.iterator().next();
			}
			
		} catch (javax.persistence.NoResultException|NoSuchElementException e ) {
			return null;
		}
		
	}

	@Override
	public Set<Room> findSurveyRooms(Player player) {
		
		Set<Room> set = new HashSet<>();
		
		long current = System.currentTimeMillis(); 
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		Root <PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("player"), player));
		
		Query<PlayerRoomJT> roomJT = ses.createQuery(query);
		
		try {
			
			List<PlayerRoomJT> roomJTList = roomJT.list();
			
			for (int i = 0; i < roomJTList.size(); i++) {
				
				PlayerRoomJT r = roomJTList.get(i);
				Room room = r.getRoom();
				
				Date created = room.getCreated();
				long cTime = created.getTime();
				boolean createdBool = ( cTime <= (current - 600));
				
				Date left = room.getClosed();
				if (left != null) {
					
					long lTime = left.getTime();
					
					boolean leftBool = ( lTime >= (current - 86400));
					
					if(createdBool && leftBool) {
						set.add(room);
					}
				}
			}
			
			return set;
			
		} catch (javax.persistence.NoResultException|NoSuchElementException e ) {
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public void update(PlayerRoomJT pr) {
		
		Session s = sf.getCurrentSession();
		s.update(pr);
		
	}

	@Override
	public void delete(PlayerRoomJT pr) {
		
		Session s = sf.getCurrentSession();
		s.delete(pr);
		
	}

}
