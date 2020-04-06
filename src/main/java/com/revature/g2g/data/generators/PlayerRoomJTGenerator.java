package com.revature.g2g.data.generators;

import java.util.Date;
import java.util.Random;
import java.util.Set;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.handlers.RoomHandler;

public class PlayerRoomJTGenerator implements DataGenerator {
	private static PlayerHandler playerHandler = new PlayerHandler();
	private static RoomHandler roomHandler= new RoomHandler();
	private static PlayerRoomJTHandler playerRoomJTHandler = new PlayerRoomJTHandler();
	private static Set<Player> players = playerHandler.findByRole(PlayerRole.PLAYER);
	private static Set<Room> rooms = roomHandler.findByStatus(RoomStatus.OPENED);
	@Override
	public void generate() {
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
		int random = (int) Math.floor(Math.random() * count);
		return (Player) players.toArray()[random];
	}
	private Room randRoom() {
		int count = rooms.size();
		int random = (int) Math.floor(Math.random() * count);
		return (Room) rooms.toArray()[random];
	}
}