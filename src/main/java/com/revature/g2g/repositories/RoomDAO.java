package com.revature.g2g.repositories;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.helpers.HibernateUtil;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoomDAO implements IRoomDAO {
	
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(Room r) {
		
		Session ses = sf.getCurrentSession();
		ses.save(r);
		
	}

	@Override
	public Room findById(int id) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		Room r = ses.get(Room.class, id);
		
		tx.commit();
		HibernateUtil.closeSession();
		return r;
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
		Set<Room> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		
		Query<Room> room = ses.createQuery(query);
		
		set = room.getResultStream()
				.collect(Collectors.toSet());
		
		tx.commit();
		HibernateUtil.closeSession();
		
		return set;
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
			
			return null;
			
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
