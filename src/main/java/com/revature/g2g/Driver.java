package com.revature.g2g;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.repositories.GameDAO;
import com.revature.g2g.repositories.PlayerDAO;
import com.revature.g2g.repositories.PlayerRoomJTDAO;
import com.revature.g2g.repositories.RoomDAO;
import com.revature.g2g.repositories.SkillDAO;
import com.revature.g2g.services.helpers.HibernateUtil;

public class Driver {
	public static void main(String[] args) {
//		Session s = HibernateUtil.getSession();
//		Transaction tx = s.beginTransaction();
//
//		Skill soft = new Skill();
//		Skill technical = new Skill();
//		Skill participation = new Skill();
//		soft.setName("Soft Skills");
//		technical.setName("Technical Proficiency");
//		participation.setName("Participation");
//		
//		s.save(soft);
//		s.save(technical);
//		s.save(participation);
//		
//		tx.commit();
//		HibernateUtil.closeSession();
//		
//		Session s = HibernateUtil.getSession();
//		Transaction tx = s.beginTransaction();
//		
//		Player player = new Player();
//		player.setPlayerUsername("kfilio");
//		player.setPlayerEmail("kfilio@email.com");
//		player.setPlayerPassword("password");
//		player.setPlayerRole(PlayerRole.PLAYER);
		
//		Player player = new Player();
//		player.setPlayerUsername("nyuan");
//		player.setPlayerEmail("nyuan@email.com");
//		player.setPlayerPassword("password");
//		player.setPlayerRole(PlayerRole.MODERATOR);
		
//		Player player = new Player();
//		player.setPlayerUsername("plawrence");
//		player.setPlayerEmail("plawrence@email.com");
//		player.setPlayerPassword("password");
//		player.setPlayerRole(PlayerRole.ADMIN);
		
		System.out.println(new SkillDAO().findAll());
		
//		Room room = new RoomDAO().findById(47);
//		
//		System.out.println(new PlayerRoomJTDAO().findPlayers(room));
	
		//System.out.println(new PlayerRoomJTDAO().countCurrentPlayers());
		
	}
}
