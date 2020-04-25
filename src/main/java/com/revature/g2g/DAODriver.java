package com.revature.g2g;

import java.util.Optional;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.models.Player;
import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.repositories.IPlayerRoomJTDAO;
import com.revature.g2g.repositories.ISkillPlayerChangeJTDAO;

public class DAODriver {
//	private static SkillHandler skillHandler = new SkillHandler();
//	private static PlayerHandler playerHandler = new PlayerHandler();
//	private static SkillPlayerJTHandler skillPlayerJTHandler = new SkillPlayerJTHandler();
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ISkillPlayerChangeJTDAO dao = ac.getBean(ISkillPlayerChangeJTDAO.class);
		System.out.println(dao.hashCode());
		IPlayerDAO daoP = ac.getBean(IPlayerDAO.class);
		IPlayerRoomJTDAO daoPR = ac.getBean(IPlayerRoomJTDAO.class);
		Optional<Player> player = daoP.findById(17L);
		if(player.isPresent()) {
			System.out.println(daoPR.findSurveyRooms(player.get()));
		}
	}
}
