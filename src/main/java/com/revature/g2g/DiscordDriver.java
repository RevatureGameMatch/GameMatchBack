package com.revature.g2g;

import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;

public class DiscordDriver {
	public static void main(String[] args) {
		JDA jda = JDASingleton.getJda();
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		ac.getBean(DummyDataDriver.class).generate();
//		List<Role> roles = jda.getRoles();
//		for(Role role: roles) {
//			System.out.println(role.getName());
//			System.out.println(role.getIdLong());
//		}
	}
}
