package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.services.helpers.HibernateUtil;

public class SkillGameJTDAO implements ISkillGameJTDAO {

	@Override
	public void insert(SkillGameJT sg) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(sg);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public SkillGameJT findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SkillGameJT> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Game> findBySkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Skill findTopSkill(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Skill> findByGame(Game game) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SkillGameJT sg) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(sg);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(SkillGameJT sg) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(sg);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

}
