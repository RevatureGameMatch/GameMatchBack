package com.revature.g2g.data;

import com.revature.g2g.data.generators.SkillGameJTGenerator;
import com.revature.g2g.data.generators.SkillGenerator;

public class DummyDataDriver {
	public static void main(String[] args) {
//		new PlayerGenerator().generate();
//		new RoomGenerator().generate();
//		new PlayerRoomJTGenerator().generate();
//		new GameGenerator().generate();
		new SkillGenerator().generate();
		new SkillGameJTGenerator().generate();
//		new SkillPlayerChangeJTGenerator().generate();
//		new SkillPlayerJTGenerator().generate();
//		new SkillRoomJTGenerator().generate();
	}
}
