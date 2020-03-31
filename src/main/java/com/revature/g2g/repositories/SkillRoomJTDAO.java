package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;
import com.revature.g2g.services.helpers.HibernateUtil;

public class SkillRoomJTDAO implements ISkillRoomJTDAO {

	@Override
	public void insert(SkillRoomJT sr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(sr);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public SkillRoomJT findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SkillRoomJT findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SkillRoomJT> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SkillRoomJT> findBySkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SkillRoomJT> findByRoom(Room room) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SkillRoomJT sr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(sr);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(SkillRoomJT sr) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(sr);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

}
