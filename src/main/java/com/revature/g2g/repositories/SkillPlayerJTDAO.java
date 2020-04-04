package com.revature.g2g.repositories;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.helpers.HibernateUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerJTDAO implements ISkillPlayerJTDAO{

	@Override
	public void insert(SkillPlayerJT sp) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(sp);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public SkillPlayerJT findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findValue(Player player, Skill skill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<SkillPlayerJT> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<SkillPlayerJT> findBySkill(Skill skill) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(SkillPlayerJT sp) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(sp);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(SkillPlayerJT sp) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(sp);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

}
