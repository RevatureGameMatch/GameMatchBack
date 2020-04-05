package com.revature.g2g.data.generators;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.data.genTemplates.SkillPriorityTemplate;
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
		make("DnD5e", new SkillPriorityTemplate[] { 
				new SkillPriorityTemplate("Fun to Play With",10), 
				new SkillPriorityTemplate("Good at Game",5), 
				new SkillPriorityTemplate("Roleplay",7)
				});
	}
	private boolean make(String gameName, SkillPriorityTemplate[] skills) {
		Game game = gameHandler.findByName(gameName);
		if(game == null) {return false;};
		for (SkillPriorityTemplate skillTemplate : skills) {
			Skill skill = skillHandler.findByName(skillTemplate.getSkill());
			if(skill == null) {continue;}
			SkillGameJT skillGameJT = null;
			boolean preExisting = true;
			if((skillGameJT = skillGameJTHandler.findBySkillGame(skill, game)) == null) {
				skillGameJT = new SkillGameJT();
				preExisting = false;
			}
			skillGameJT.setGame(game);
			skillGameJT.setSkill(skill);
			skillGameJT.setRelevance(skillTemplate.getPriority());
			if(preExisting) {
				skillGameJTHandler.update(skillGameJT);
			}else {
				skillGameJTHandler.insert(skillGameJT);
			}
		}
		return true;
	}
}