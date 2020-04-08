package com.revature.g2g.services.business;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.services.handlers.PlayerHandler;

public class ServicesDriver {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		PlayerRoomService playerRoomService = ac.getBean(PlayerRoomService.class);
		PlayerHandler playerHandler = ac.getBean(PlayerHandler.class);
		Player philip = playerHandler.findByUsername("Philip");
		List<Room> qualifiedRooms = playerRoomService.getQualifiedRooms(philip, RoomPlayStyle.CASUAL);
		System.out.println(qualifiedRooms);
	}
}
