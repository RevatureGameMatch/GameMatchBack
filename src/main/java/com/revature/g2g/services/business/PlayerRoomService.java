package com.revature.g2g.services.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.models.SkillRoomJT;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
import com.revature.g2g.services.handlers.SkillRoomJTHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomService {
	SkillPlayerJTHandler skillPlayerJTHandler;
	SkillRoomJTHandler skillRoomJTHandler;
	RoomHandler roomHandler;
	LoggerSingleton loggerSingleton;
	@Autowired
	public PlayerRoomService(SkillPlayerJTHandler skillPlayerJTHandler, SkillRoomJTHandler skillRoomJTHandler,
			RoomHandler roomHandler, LoggerSingleton loggerSingleton) {
		super();
		this.skillPlayerJTHandler = skillPlayerJTHandler;
		this.skillRoomJTHandler = skillRoomJTHandler;
		this.roomHandler = roomHandler;
		this.loggerSingleton = loggerSingleton;
	}
	public List<Room> getQualifiedRooms(Player player, RoomPlayStyle style) {
		List<Room> rooms = roomHandler.findByStatusAndPlayStyle(RoomStatus.JOINING, style);
		return fetchQualifiedRooms(player, rooms);
	}
	public List<Room> getQualifiedRooms(Player player, Game game) {
		List<Room> rooms = roomHandler.findByStatusAndGame(RoomStatus.JOINING, game);
		return fetchQualifiedRooms(player, rooms);
	}
	public List<Room> getQualifiedRooms(Player player, RoomPlayStyle style, Game game) {
		List<Room> rooms = roomHandler.findByStatusAndPlayStyleAndGame(RoomStatus.JOINING, style, game);
		return fetchQualifiedRooms(player, rooms);
	}
	private List<Room> fetchQualifiedRooms(Player player, List<Room> rooms) {
		loggerSingleton.getBusinessLog().trace("PlayerRoomService: Starting to check get Qualified Rooms");
		List<Room> result = new ArrayList<>();
		List<SkillPlayerJT> playerSkillsList = skillPlayerJTHandler.findByPlayer(player);
		SkillPlayerJT[] playerSkills = playerSkillsList.toArray( new SkillPlayerJT[0]);
		for(Room room : rooms) {
			if(checkQualfiedRoom(player, room, playerSkills) ) {
				result.add(room);
			}
		}
		return result;
	}
	public boolean checkQualfiedRoom(Player player, Room room, SkillPlayerJT[] playerSkills) {
		if(playerSkills == null) {
			List<SkillPlayerJT> playerSkillsList = skillPlayerJTHandler.findByPlayer(player);
			playerSkills = playerSkillsList.toArray( new SkillPlayerJT[0]);
		}
		int playerSkillsLen = playerSkills.length;
		boolean qualified = true;
		List<SkillRoomJT> roomSkillsList = skillRoomJTHandler.findByRoom(room);
		SkillRoomJT[] roomSkills = roomSkillsList.toArray( new SkillRoomJT[0]);
		int roomSkillsLen = roomSkills.length;
		for (int a=0; a<roomSkillsLen; a++) {
			if(roomSkills[a].getMinValue() == 0) {
				continue;
			}
			boolean found = false;
			for (int b=0; b<playerSkillsLen; b++) {
				//If the player skill is the same as the room skill
				if (roomSkills[a].getSkill().equals(playerSkills[b].getSkill())) {
					found = true;
					//If the player skill does not meet the standards to be qualified
					if(playerSkills[b].getValue() <= roomSkills[a].getMinValue()) {
						return false;
					}
				}
			}
			if(!found) {
				return false;
			}
		}
		return qualified;
	}
}