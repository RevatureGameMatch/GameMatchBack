package com.revature.g2g;

import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.repositories.IPlayerRoomJTDAO;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;

public class DAODriver {
//	private static SkillHandler skillHandler = new SkillHandler();
//	private static PlayerHandler playerHandler = new PlayerHandler();
//	private static SkillPlayerJTHandler skillPlayerJTHandler = new SkillPlayerJTHandler();
	public static void main(String[] args) {
//		Player kayla = playerHandler.findByUsername("Kayla");
//		Skill team = skillHandler.findByName("Empower Team Decisions");
//		System.out.println(skillPlayerJTHandler.findValue(kayla, team));
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		//IPlayerDAO dao = ac.getBean(IPlayerDAO.class);
		IPlayerRoomJTDAO prdao = ac.getBean(IPlayerRoomJTDAO.class);
		
		//System.out.println(dao.findByUsername("asdf"));
		System.out.println(prdao.countCurrentPlayers());
		System.out.println(prdao.findById(341).getJoined());
		
		if (prdao.findById(341).getLeft() == null) {
			System.out.println("yay!");
		}
		
	}
}
