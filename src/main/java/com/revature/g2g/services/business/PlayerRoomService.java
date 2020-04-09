package com.revature.g2g.services.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.models.SkillRoomJT;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
import com.revature.g2g.services.handlers.SkillRoomJTHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomService {
	@Autowired
	SkillPlayerJTHandler skillPlayerJTHandler;
	@Autowired
	SkillRoomJTHandler skillRoomJTHandler;
	@Autowired
	RoomHandler roomHandler;
	public List<Room> getQualifiedRooms(Player player, RoomPlayStyle style) {
		List<Room> result = new ArrayList<Room>();
		Set<SkillPlayerJT> playerSkillsSet = skillPlayerJTHandler.findByPlayer(player);
		SkillPlayerJT[] playerSkills = playerSkillsSet.toArray( new SkillPlayerJT[0]);
		int playerSkillsLen = playerSkills.length;
		Set<Room> rooms = roomHandler.findStatusPlayStyle(RoomStatus.JOINING, style);
		for(Room room : rooms) {
			boolean qualified = true;
			Set<SkillRoomJT> roomSkillsSet = skillRoomJTHandler.findByRoom(room);
			SkillRoomJT[] roomSkills = roomSkillsSet.toArray( new SkillRoomJT[0]);
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
							qualified = false;
							break;
						}
					}
				}
				if(!found || !qualified) {
					qualified = false;
					break;
				}
			}
			if(qualified) {
				result.add(room);
			}
		}
		return result;
	}
}