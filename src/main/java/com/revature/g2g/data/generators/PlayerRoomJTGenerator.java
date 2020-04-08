package com.revature.g2g.data.generators;

import java.util.Date;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomJTGenerator implements DataGenerator {
	@Autowired
	private PlayerHandler playerHandler;
	@Autowired
	private RoomHandler roomHandler;
	@Autowired
	private PlayerRoomJTHandler playerRoomJTHandler;
	@Autowired
	private LoggerSingleton loggerSingleton;
	private Set<Player> players;
	private Set<Room> rooms;
	@Override
	public void generate() {
		players = playerHandler.findAll();
		rooms = roomHandler.findByStatus(RoomStatus.OPENED);
		for(int a=0; a<20; a++) {
			PlayerRoomJT playerRoomJT = new PlayerRoomJT();
			playerRoomJT.setPlayer(randPlayer());
			playerRoomJT.setRoom(randRoom());
			playerRoomJT.setJoined(new Date());
			playerRoomJT.setLeft(new Date(new Date().getTime() + new Random().nextInt(400)));
			playerRoomJTHandler.insert(playerRoomJT);
		}
		for(int a=0; a<10; a++) {
			PlayerRoomJT playerRoomJT = new PlayerRoomJT();
			playerRoomJT.setPlayer(randPlayer());
			playerRoomJT.setRoom(randRoom());
			playerRoomJT.setJoined(new Date());
			playerRoomJTHandler.insert(playerRoomJT);
		}
	}
	private Player randPlayer() {
		int count = players.size();
		if(count > 0) {
			int random = new Random().nextInt(count);
			return (Player) players.toArray()[random];
		}else {
			loggerSingleton.getExceptionLogger().warn("PlayerRoomGenerator: player size 0");
			return null;
		}
	}
	private Room randRoom() {
		int count = rooms.size();
		if(count > 0) {
			int random = new Random().nextInt(count);
			return (Room) rooms.toArray()[random];
		}else {
			loggerSingleton.getExceptionLogger().warn("PlayerRoomGenerator: room size 0");
			return null;
		}
	}
}