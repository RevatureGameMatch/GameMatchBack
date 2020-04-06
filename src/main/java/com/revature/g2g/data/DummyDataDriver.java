package com.revature.g2g.data;

import com.revature.g2g.data.generators.GameGenerator;
import com.revature.g2g.data.generators.PlayerGenerator;
import com.revature.g2g.data.generators.PlayerRoomJTGenerator;
import com.revature.g2g.data.generators.RoomGenerator;
import com.revature.g2g.data.generators.SkillGameJTGenerator;
import com.revature.g2g.data.generators.SkillGenerator;
import com.revature.g2g.data.generators.SkillPlayerJTGenerator;
import com.revature.g2g.data.generators.SkillRoomJTGenerator;

public class DummyDataDriver {
	public static void main(String[] args) {
//		new PlayerGenerator().generate();
//		new RoomGenerator().generate();
//		new PlayerRoomJTGenerator().generate();
//		new GameGenerator().generate();
//		new SkillGenerator().generate();
//		new SkillGameJTGenerator().generate();
		new SkillPlayerJTGenerator().generate();
//		new SkillRoomJTGenerator().generate();
	}
}
