package com.revature.g2g.repositories;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.services.helpers.HibernateUtil;

public class SkillPlayerChangeJTDAO implements ISkillPlayerChangeJTDAO{

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
