package com.revature.g2g.services.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationContextSingleton {
	private static ClassPathXmlApplicationContext ac;
	private ApplicationContextSingleton() {
	}
	public static ClassPathXmlApplicationContext getApplicationContext() {
		if(ac == null) {
			ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		return ac;
	}
}
