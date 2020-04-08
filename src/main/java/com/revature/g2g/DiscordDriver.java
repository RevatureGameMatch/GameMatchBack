package com.revature.g2g;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.data.DummyDataDriver;
import com.revature.g2g.services.jda.JDASingleton;

public class DiscordDriver {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ac.getBean(JDASingleton.class).getJda();
		ac.getBean(DummyDataDriver.class).generate();
	}
}
