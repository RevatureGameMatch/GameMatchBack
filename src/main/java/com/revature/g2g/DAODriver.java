package com.revature.g2g;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.repositories.IGameDAO;
import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.repositories.IPlayerRoomJTDAO;
import com.revature.g2g.repositories.IRoomDAO;
import com.revature.g2g.repositories.ISkillRoomJTDAO;
import com.revature.g2g.repositories.PlayerDAO;
import com.revature.g2g.repositories.PlayerRoomJTDAO;

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
		
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		IPlayerDAO dao = ac.getBean(IPlayerDAO.class);
//		IPlayerRoomJTDAO daopr = ac.getBean(IPlayerRoomJTDAO.class);
//		
//		System.out.println(daopr.findAll());
		
		long millis=System.currentTimeMillis(); 
		System.out.println(millis-600);
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		IPlayerRoomJTDAO dao = ac.getBean(IPlayerRoomJTDAO.class);
		
//		Date time = dao.findById(155).getLeft();
//		long mil = time.getTime();
//		
//		System.out.println(mil);
		
		ISkillRoomJTDAO srDAO = ac.getBean(ISkillRoomJTDAO.class);
		
		IRoomDAO rDAO = ac.getBean(IRoomDAO.class);
		
		Room r = rDAO.findById(144);
		
		System.out.println(srDAO.findSkillsByRoom(r));
		
	}
}
