package com.revature.g2g;

import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.utils.cache.SnowflakeCacheView;

public class Driver {
	public static void main(String[] args) {
//		Session s = HibernateUtil.getSession();
//		Transaction tx = s.beginTransaction();
//
//		Skill soft = new Skill();
//		Skill technical = new Skill();
//		Skill participation = new Skill();
//		soft.setName("Soft Skills");
//		technical.setName("Technical Proficiency");
//		participation.setName("Participation");
//		
//		s.save(soft);
//		s.save(technical);
//		s.save(participation);
//		
//		tx.commit();
//		HibernateUtil.closeSession();
		JDASingleton.getJda();
	}
}
