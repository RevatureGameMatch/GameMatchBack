package com.revature.g2g.services.helpers;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class HibernateUtil {
	private HibernateUtil() {
	}
	
	private static Session session;
	
	private static SessionFactory sf =
			new Configuration().configure("hibernate.cfg.xml")
				.buildSessionFactory();
	
	public static Session getSession() {
		if(session == null) {
			session = sf.openSession();
		}
		
		return session;
	}
	
	public static void closeSession() {
		session.close();
		session = null;
	}
}
