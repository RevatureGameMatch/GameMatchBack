package com.revature.g2g.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.services.helpers.HibernateUtil;

@Transactional
@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerChangeJTDAO implements ISkillPlayerChangeJTDAO{
	@Autowired
	private SessionFactory sf;

	@Override
	public void insert(SkillPlayerChangeJT spc) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.save(spc);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void update(SkillPlayerChangeJT spc) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.update(spc);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

	@Override
	public void delete(SkillPlayerChangeJT spc) {
		Session ses = HibernateUtil.getSession();
		Transaction tx = ses.beginTransaction();
		
		ses.delete(spc);
		
		tx.commit();
		HibernateUtil.closeSession();
	}

}
