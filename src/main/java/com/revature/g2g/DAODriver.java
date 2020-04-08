package com.revature.g2g;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.repositories.IPlayerRoomJTDAO;
import com.revature.g2g.repositories.PlayerDAO;

public class DAODriver {
//	private static SkillHandler skillHandler = new SkillHandler();
//	private static PlayerHandler playerHandler = new PlayerHandler();
//	private static SkillPlayerJTHandler skillPlayerJTHandler = new SkillPlayerJTHandler();
	public static void main(String[] args) {
//		Player kayla = playerHandler.findByUsername("Kayla");
//		Skill team = skillHandler.findByName("Empower Team Decisions");
//		System.out.println(skillPlayerJTHandler.findValue(kayla, team));
		
		
		//System.out.println(new PlayerRoomJTDAO().countCurrentPlayers());
		//ApplictionContext ac = ClassPathXmlApplicationContext
		
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPlayerDAO dao = ac.getBean(IPlayerDAO.class);
		IPlayerRoomJTDAO daopr = ac.getBean(IPlayerRoomJTDAO.class);
		
		System.out.println(daopr.findAll());
		
	}
}
