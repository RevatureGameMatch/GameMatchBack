package com.revature.g2g.data.generators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.data.gentemplates.SkillPriorityTemplate;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.handlers.SkillHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillGameJTGenerator implements DataGenerator {
	private GameHandler gameHandler;
	private SkillHandler skillHandler;
	private SkillGameJTHandler skillGameJTHandler;
	@Autowired
	public SkillGameJTGenerator(GameHandler gameHandler, SkillHandler skillHandler,
			SkillGameJTHandler skillGameJTHandler) {
		super();
		this.gameHandler = gameHandler;
		this.skillHandler = skillHandler;
		this.skillGameJTHandler = skillGameJTHandler;
	}
	@Override
	public void generate() {
		List<Game> games = gameHandler.findAll();
		for(Game game: games) {
			make(game.getName(), new SkillPriorityTemplate[] {
				new SkillPriorityTemplate("Fun to Play With",5),
				new SkillPriorityTemplate("Good at Game", 5),
			});
		}
		make("DnD5e", new SkillPriorityTemplate[] { 
				new SkillPriorityTemplate("Fun to Play With",10), 
				new SkillPriorityTemplate("Good at Game",5), 
				new SkillPriorityTemplate("Roleplay",7)
				});
	}
	private boolean make(String gameName, SkillPriorityTemplate[] skills) {
		Game game = gameHandler.findByName(gameName);
		if(game == null) {return false;}
		for (SkillPriorityTemplate skillTemplate : skills) {
			Skill skill = skillHandler.findByName(skillTemplate.getSkill());
			if(skill == null) {continue;}
			SkillGameJT skillGameJT = null;
			if((skillGameJT = skillGameJTHandler.findBySkillGame(skill, game)) == null) {
				skillGameJT = new SkillGameJT();
			}
			skillGameJT.setGame(game);
			skillGameJT.setSkill(skill);
			skillGameJT.setRelevance(skillTemplate.getPriority());
			skillGameJTHandler.save(skillGameJT);
		}
		return true;
	}
}