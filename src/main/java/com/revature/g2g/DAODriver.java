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
import com.revature.g2g.repositories.ISkillPlayerChangeJTDAO;
import com.revature.g2g.repositories.ISkillRoomJTDAO;
import com.revature.g2g.repositories.PlayerDAO;
import com.revature.g2g.repositories.PlayerRoomJTDAO;

public class DAODriver {
//	private static SkillHandler skillHandler = new SkillHandler();
//	private static PlayerHandler playerHandler = new PlayerHandler();
//	private static SkillPlayerJTHandler skillPlayerJTHandler = new SkillPlayerJTHandler();
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		ISkillPlayerChangeJTDAO dao = ac.getBean(ISkillPlayerChangeJTDAO.class);
		System.out.println(dao.hashCode());
	}
}
