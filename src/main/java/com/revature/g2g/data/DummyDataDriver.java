package com.revature.g2g.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.generators.GameGenerator;
import com.revature.g2g.data.generators.PlayerGenerator;
import com.revature.g2g.data.generators.PlayerRoomJTGenerator;
import com.revature.g2g.data.generators.RoomGenerator;
import com.revature.g2g.data.generators.SkillGameJTGenerator;
import com.revature.g2g.data.generators.SkillGenerator;
import com.revature.g2g.data.generators.SkillPlayerJTGenerator;
import com.revature.g2g.data.generators.SkillRoomJTGenerator;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class DummyDataDriver {
	@Autowired
	private LoggerSingleton loggerSingleton;
	@Autowired
	private PlayerGenerator playerGenerator;
	@Autowired
	private GameGenerator gameGenerator;
	@Autowired
	private SkillGenerator skillGenerator;
	@Autowired
	private SkillGameJTGenerator skillGameJTGenerator;
	@Autowired
	private SkillPlayerJTGenerator skillPlayerJTGenerator;
	@Autowired
	private RoomGenerator roomGenerator;
	@Autowired
	private SkillRoomJTGenerator skillRoomJTGenerator;
	@Autowired
	private PlayerRoomJTGenerator playerRoomJTGenerator;
	public void generate() {
//		throw new RuntimeException("Generation is turned off right now");
		loggerSingleton.getBusinessLog().trace("Generating Players");
		playerGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generating Games");
		gameGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generating Skills");
		skillGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generating SkillGameJT");
		skillGameJTGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generating SkillPlayerJT");
		skillPlayerJTGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generating Rooms");
		roomGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generating SkillRoomJT");
		skillRoomJTGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generating PlayerRoomJT");
		playerRoomJTGenerator.generate();
		loggerSingleton.getBusinessLog().trace("Generation Complete");
	}
}
