package com.revature.g2g;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.Skill;
import com.revature.g2g.services.helpers.HibernateUtil;

public class Driver {
	public static void main(String[] args) {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();

		Skill soft = new Skill();
		Skill technical = new Skill();
		Skill participation = new Skill();
		soft.setName("Soft Skills");
		technical.setName("Technical Proficiency");
		participation.setName("Participation");
		
		s.save(soft);
		s.save(technical);
		s.save(participation);
		
		tx.commit();
		HibernateUtil.closeSession();
	}
}
