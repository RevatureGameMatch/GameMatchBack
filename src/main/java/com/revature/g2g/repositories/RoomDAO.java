package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.helpers.HibernateUtil;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Room> findAll() {
		// TODO Auto-generated method stub
		return null;
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
