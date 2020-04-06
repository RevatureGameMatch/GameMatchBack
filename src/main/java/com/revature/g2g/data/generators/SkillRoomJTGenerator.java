package com.revature.g2g.data.generators;

import java.util.Random;
import java.util.Set;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillRoomJTHandler;

public class SkillRoomJTGenerator implements DataGenerator{
	private static SkillHandler skillHandler = new SkillHandler();
	private static RoomHandler roomHandler = new RoomHandler();
	private static SkillRoomJTHandler skillRoomJTHandler = new SkillRoomJTHandler();
	private static Set<Skill> skills = skillHandler.findAll();

	@Override
	public void generate() {
		Set<Room> rooms = roomHandler.findByStatus(RoomStatus.OPENED);
		for (Room room : rooms) {
			make(room);
		}
	}
	private void make(Room room){
		int numberOfSkills = new Random().nextInt(4);
		for (int a = 0; a < numberOfSkills; a++) {
			SkillRoomJT skillRoomJT = new SkillRoomJT();
			skillRoomJT.setMinValue(new Random().nextInt(10));
			skillRoomJT.setRoom(room);
			skillRoomJT.setSkill(randomSkill());
			skillRoomJTHandler.insert(skillRoomJT);
		}
	}
	private Skill randomSkill() {
		int count = skills.size();
		int random = new Random().nextInt(count);
		return (Skill) skills.toArray()[random];
	}
}