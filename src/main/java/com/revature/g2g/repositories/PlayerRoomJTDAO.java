package com.revature.g2g.repositories;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.services.helpers.HibernateUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomJTDAO implements IPlayerRoomJTDAO {

	@Override
	public void insert(PlayerRoomJT pr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(pr);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public PlayerRoomJT findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		PlayerRoomJT room = ses.get(PlayerRoomJT.class, id);
		
		tx.commit();
		HibernateUtil.closeSession();
		return room;
	}

	@Override
	public int countCurrentPlayers() {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		CriteriaQuery <Long> subquery = builder.createQuery(Long.class);
		
		Root<PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		
		subquery.select(builder.count(root.get("left")));
		subquery.where(builder.equal(root.get("left"), null));
		
		Query<Long> total = ses.createQuery(subquery);
		
		Long number = total.getSingleResult();
		int finalNumber = number.intValue();
		
		tx.commit();
		HibernateUtil.closeSession();
		return finalNumber;
	}

	@Override
	public int countCurrentPlayers(Room room) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		CriteriaQuery <Long> subquery = builder.createQuery(Long.class);
		
		Root <PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		
		subquery.select(builder.count(root.get("left")));
		subquery.where(builder.equal(root.get("room"), room));
		subquery.where(builder.equal(root.get("left"), null));
		
		Query<Long> total = ses.createQuery(subquery);
		
		Long number = total.getSingleResult();
		int finalNumber = number.intValue();
		
		tx.commit();
		HibernateUtil.closeSession();
		return finalNumber;
	}

	@Override
	public Set<PlayerRoomJT> findAll() {
		Set<PlayerRoomJT> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		Query <PlayerRoomJT> room = ses.createQuery(query);
		
		set = room.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		return set;
		
	}

	@Override
	public Set<Player> findPlayers(Room room) {
		Set<Player> set = new HashSet<>();
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		Root <PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("room"), room));
		
		Query<PlayerRoomJT> roomJT = ses.createQuery(query);
		
		List<PlayerRoomJT> roomJTList = roomJT.list();
		
		for (int i = 0; i < roomJTList.size(); i++) {
			
			PlayerRoomJT r = roomJTList.get(i);
			Player player = r.getPlayer();
			
			if(player != null) {
				set.add(player);
			}
		}
		
		tx.commit();
		HibernateUtil.closeSession();
		return set;
		
	}

	@Override
	public Set<Room> findRooms(Player player) {
		Set<Room> set = new HashSet<>();
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery <PlayerRoomJT> query = builder.createQuery(PlayerRoomJT.class);
		
		Root <PlayerRoomJT> root = query.from(PlayerRoomJT.class);
		
		query.select(root).where(builder.equal(root.get("player"), player));
		
		Query<PlayerRoomJT> roomJT = ses.createQuery(query);
		
		List<PlayerRoomJT> roomJTList = roomJT.list();
		
		for (int i = 0; i < roomJTList.size(); i++) {
			
			PlayerRoomJT r = roomJTList.get(i);
			Room room = r.getRoom();
			
			if(room != null) {
				set.add(room);
			}
		}
		
		tx.commit();
		HibernateUtil.closeSession();
		return set;
	}

	@Override
	public void update(PlayerRoomJT pr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(pr);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(PlayerRoomJT pr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.delete(pr);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
