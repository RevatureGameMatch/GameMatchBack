package com.revature.g2g.data.generators;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillRoomJTHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillRoomJTGenerator implements DataGenerator{
	@Autowired
	private SkillHandler skillHandler;
	@Autowired
	private RoomHandler roomHandler;
	@Autowired
	private SkillRoomJTHandler skillRoomJTHandler;
	@Autowired
	private LoggerSingleton loggerSingleton;
	private Set<Skill> skills;
	@Override
	public void generate() {
		skills = skillHandler.findAll();
		Set<Room> rooms = roomHandler.findByStatus(RoomStatus.JOINING);
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
		if(count != 0) {
			int random = new Random().nextInt(count);
			return (Skill) skills.toArray()[random];
		}else {
			loggerSingleton.getExceptionLogger().warn("SkillRoomGenerator: Random skill count of 0");
		}
		return null;
	}
}