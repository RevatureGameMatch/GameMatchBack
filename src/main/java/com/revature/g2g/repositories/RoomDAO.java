package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoomDAO implements IRoomDAO {
	
	private SessionFactory sf;
	@PersistenceContext
	private EntityManager entityManager;
	public RoomDAO() {
		super();
		Session session = entityManager.unwrap(Session.class);
		this.sf = session.getSessionFactory();
	}

	@Override
	public void insert(Room r) {
		
		Session ses = sf.getCurrentSession();
		ses.save(r);
		
	}

	@Override
	public Room findById(int id) {
		
		Session ses = sf.getCurrentSession();	
		return ses.get(Room.class, id);
		
	}
	
	@Override
	public Room findRoomByDiscordVoice(Long discordVoiceId) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		
		Root<Room> root = query.from(Room.class);
		
		query.select(root).where(builder.equal(root.get("discordVoiceChannelId"), discordVoiceId));
		
		Query<Room> room = ses.createQuery(query);
		
		try {
			
			return room.getSingleResult();
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;
			
		}
		
	}

	@Override
	public Set<Room> findAll() {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		
		query.from(Room.class);
		
		Query<Room> room = ses.createQuery(query);
		
		try {
			
			return room.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
	
	}
	
	@Override
	public Set<Room> findStatusPlayStyle(RoomStatus status, RoomPlayStyle style){
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		
		Root<Room> root = query.from(Room.class);
		Path<Object> statusPath = root.get("status");
		Path<Object> stylePath = root.get("style");
		
		Predicate statusPredicate = builder.equal(statusPath, status);
		Predicate stylePredicate = builder.equal(stylePath, style);
		Predicate statusAndStylePredicate = builder.and(statusPredicate,stylePredicate);
		
		query.select(root).where(statusAndStylePredicate);
		
		Query<Room> room = ses.createQuery(query);
		
		try {
			
			return room.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public Set<Room> findByStatus(RoomStatus status) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		
		Root<Room> root = query.from(Room.class);
		
		query.select(root).where(builder.equal(root.get("status"), status));
		
		Query<Room> room = ses.createQuery(query);
		
		try {
			
			return room.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}

	@Override
	public Set<Room> findByPlayStyle(RoomPlayStyle style) {
		
		Session ses = sf.getCurrentSession();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		
		Root<Room> root = query.from(Room.class);
		
		query.select(root).where(builder.equal(root.get("style"), style));
		
		Query<Room> room = ses.createQuery(query);
		
		try {
			
			return room.getResultStream()
					.collect(Collectors.toSet());
			
		} catch (javax.persistence.NoResultException e) {
			
			return Collections.emptySet();
			
		}
		
	}
	public Set<Room> findByStatusGame(RoomStatus status, Game game){
		Session ses = sf.getCurrentSession();
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		Root<Room> root = query.from(Room.class);
		Path<Object> statusPath = root.get("status");
		Path<Object> gamePath = root.get("game");
		Predicate statusPredicate = builder.equal(statusPath, status);
		Predicate gamePredicate = builder.equal(gamePath, game);
		Predicate statusAndStylePredicate = builder.and(statusPredicate,gamePredicate);
		query.select(root).where(statusAndStylePredicate);
		Query<Room> room = ses.createQuery(query);
		try {
			return room.getResultStream()
					.collect(Collectors.toSet());
		} catch (javax.persistence.NoResultException e) {
			return Collections.emptySet();
		}
	}
	public Set<Room> findByStatusPlayStyleGame(RoomStatus status, RoomPlayStyle style, Game game){
		Session ses = sf.getCurrentSession();
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		Root<Room> root = query.from(Room.class);
		Path<Object> statusPath = root.get("status");
		Path<Object> stylePath = root.get("style");
		Path<Object> gamePath = root.get("game");
		Predicate statusPredicate = builder.equal(statusPath, status);
		Predicate stylePredicate = builder.equal(stylePath, style);
		Predicate gamePredicate = builder.equal(gamePath, game);
		Predicate statusAndStylePredicate = builder.and(statusPredicate, stylePredicate, gamePredicate);
		query.select(root).where(statusAndStylePredicate);
		Query<Room> room = ses.createQuery(query);
		try {
			return room.getResultStream()
					.collect(Collectors.toSet());
		} catch (javax.persistence.NoResultException e) {
			return Collections.emptySet();
		}
	}

//	@Override
//	public Set<Room> findBySkill(RoomStatus status, SkillPlayerJT[] skills) {
//		Set<Room> set = null;
//		
//		Session ses = HibernateUtil.getSession();
//		Transaction tx = ses.beginTransaction();
//		
//		CriteriaBuilder builder = ses.getCriteriaBuilder();
//		CriteriaQuery<Room> query = builder.createQuery(Room.class);
//		
//		Root<Room> root = query.from(Room.class);
//		
//		query.select(root).where(builder.equal(root.get("status"), status));
//		
//		Query<Room> room = ses.createQuery(query);
//		
//		set = room.getResultStream()
//				.collect(Collectors.toSet());
//		
//		tx.commit();
//		HibernateUtil.closeSession();
//		
//		return set;
//	}

	@Override
	public void update(Room r) {
		
		Session ses = sf.getCurrentSession();
		ses.update(r);
	}

	@Override
	public void delete(Room r) {
		
		Session ses = sf.getCurrentSession();
		ses.delete(r);
		
	}

}
