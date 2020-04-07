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
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.handlers.RoomHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomJTGenerator implements DataGenerator {
	private PlayerHandler playerHandler;
	private RoomHandler roomHandler;
	private PlayerRoomJTHandler playerRoomJTHandler;
	private Set<Player> players;
	private Set<Room> rooms;
	public PlayerRoomJTGenerator() {
		super();
	}
	@Autowired
	public PlayerRoomJTGenerator(PlayerHandler playerHandler, RoomHandler roomHandler,
			PlayerRoomJTHandler playerRoomJTHandler) {
		super();
		this.playerHandler = playerHandler;
		this.roomHandler = roomHandler;
		this.playerRoomJTHandler = playerRoomJTHandler;
		this.players = playerHandler.findByRole(PlayerRole.PLAYER);
		this.rooms = roomHandler.findByStatus(RoomStatus.OPENED);
	}
	public PlayerHandler getPlayerHandler() {
		return playerHandler;
	}
	public void setPlayerHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler;
	}
	public RoomHandler getRoomHandler() {
		return roomHandler;
	}
	public void setRoomHandler(RoomHandler roomHandler) {
		this.roomHandler = roomHandler;
	}
	public PlayerRoomJTHandler getPlayerRoomJTHandler() {
		return playerRoomJTHandler;
	}
	public void setPlayerRoomJTHandler(PlayerRoomJTHandler playerRoomJTHandler) {
		this.playerRoomJTHandler = playerRoomJTHandler;
	}
	public Set<Player> getPlayers() {
		return players;
	}
	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
	public Set<Room> getRooms() {
		return rooms;
	}
	public void setRooms(Set<Room> rooms) {
		this.rooms = rooms;
	}
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
		if(count > 0) {
			int random = new Random().nextInt(count);
			return (Player) players.toArray()[random];
		}else {
			return null;
		}
	}
	private Room randRoom() {
		int count = rooms.size();
		if(count > 0) {
			int random = new Random().nextInt(count);
			return (Room) rooms.toArray()[random];
		}else {
			return null;
		}
	}
}