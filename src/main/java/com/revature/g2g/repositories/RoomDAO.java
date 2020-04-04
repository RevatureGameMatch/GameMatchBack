package com.revature.g2g.repositories;

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
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.helpers.HibernateUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoomDAO implements IRoomDAO {

	@Override
	public void insert(Room r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(r);

		tx.commit();
		HibernateUtil.closeSession();
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
	public Set<Room> findByStatus(RoomStatus status) {
		Set<Room> set = null;
		
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		CriteriaBuilder builder = ses.getCriteriaBuilder();
		CriteriaQuery<Room> query = builder.createQuery(Room.class);
		
		Root<Room> root = query.from(Room.class);
		
		query.select(root).where(builder.equal(root.get("status"), status));
		
		Query<Room> room = ses.createQuery(query);
		
		try {
		
			set = room.getResultStream()
					.collect(Collectors.toSet());
			
			return set;
			
		} catch (javax.persistence.NoResultException e) {
			
			return null;
			
		} finally {
			
			tx.commit();
			HibernateUtil.closeSession();
			
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
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(r);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(Room r) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.delete(r);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
