package com.revature.g2g;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.data.DummyDataDriver;
import com.revature.g2g.services.jda.JDASingleton;

public class DiscordDriver {
	public static void main(String[] args) {
		JDASingleton.getJda();
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ac.getBean(DummyDataDriver.class).generate();
//		List<Role> roles = jda.getRoles();
//		for(Role role: roles) {
//			System.out.println(role.getName());
//			System.out.println(role.getIdLong());
//		}
	}
}
