package com.revature.g2g.data;

import com.revature.g2g.data.generators.GameGenerator;
import com.revature.g2g.data.generators.PlayerGenerator;
import com.revature.g2g.data.generators.PlayerRoomJTGenerator;
import com.revature.g2g.data.generators.RoomGenerator;
import com.revature.g2g.data.generators.SkillGameJTGenerator;
import com.revature.g2g.data.generators.SkillGenerator;
import com.revature.g2g.data.generators.SkillPlayerJTGenerator;
import com.revature.g2g.data.generators.SkillRoomJTGenerator;
import com.revature.g2g.services.helpers.LoggerSingleton;

public class DummyDataDriver {
	public static void main(String[] args) {
		generate();
	}
	public static void generate() {
		LoggerSingleton.getBusinessLog().trace("Generating Players");
		new PlayerGenerator().generate();
		LoggerSingleton.getBusinessLog().trace("Generating Games");
		new GameGenerator().generate();
		LoggerSingleton.getBusinessLog().trace("Generating Skills");
		new SkillGenerator().generate();
		LoggerSingleton.getBusinessLog().trace("Generating SkillGameJT");
		new SkillGameJTGenerator().generate();
		LoggerSingleton.getBusinessLog().trace("Generating SkillPlayerJT");
		new SkillPlayerJTGenerator().generate();
		LoggerSingleton.getBusinessLog().trace("Generating Rooms");
		new RoomGenerator().generate();
		LoggerSingleton.getBusinessLog().trace("Generating SkillRoomJT");
		new SkillRoomJTGenerator().generate();
		LoggerSingleton.getBusinessLog().trace("Generating PlayerRoomJT");
		new PlayerRoomJTGenerator().generate();
	}
}
