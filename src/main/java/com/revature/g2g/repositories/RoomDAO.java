package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
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
		return set;
	}

	@Override
	public Set<Room> findByStatus(RoomStatus status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Room> findBySkill(RoomStatus status, SkillPlayerJT[] skills) {
		// TODO Auto-generated method stub
		return null;
	}

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
