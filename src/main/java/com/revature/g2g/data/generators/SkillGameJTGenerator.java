package com.revature.g2g.data.generators;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.handlers.SkillHandler;

public class SkillGameJTGenerator implements DataGenerator {
	private static GameHandler gameHandler = new GameHandler();
	private static SkillHandler skillHandler = new SkillHandler();
	private static SkillGameJTHandler skillGameJTHandler = new SkillGameJTHandler();

	@Override
	public void generate() {
		// TODO Auto-generated method stub

	}
	private boolean make(String gameName, String[] skills) {
		Game game = gameHandler.findByName(gameName);
		if(game == null) {return false;};
		for (String skillName : skills) {
			Skill skill = skillHandler.findByName(skillName);
			if(skill == null) {continue;}
			SkillGameJT skillGameJT = new SkillGameJT();
			//TODO see if it already exists with findBySkillGame
		}
		return true;
	}

}
