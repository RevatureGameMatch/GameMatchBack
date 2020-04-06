package com.revature.g2g;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;

public class DAODriver {
	private static SkillHandler skillHandler = new SkillHandler();
	private static PlayerHandler playerHandler = new PlayerHandler();
	private static SkillPlayerJTHandler skillPlayerJTHandler = new SkillPlayerJTHandler();
	public static void main(String[] args) {
		Player kayla = playerHandler.findByUsername("Kayla");
		Skill team = skillHandler.findByName("Empower Team Decisions");
		System.out.println(skillPlayerJTHandler.findValue(kayla, team));
	}
}
