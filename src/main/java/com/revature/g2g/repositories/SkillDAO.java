package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.Skill;
import com.revature.g2g.services.helpers.HibernateUtil;

public class SkillDAO implements ISkillDAO {

	@Override
	public void insert(Skill s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.save(s);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public Skill findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Skill findByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Skill> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Skill> findByParent(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Skill s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.update(s);

		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(Skill s) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();

		ses.delete(s);

		tx.commit();
		HibernateUtil.closeSession();
	}

}
