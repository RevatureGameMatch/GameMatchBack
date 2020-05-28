package com.revature.g2g.data.generators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.SkillHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillGenerator implements DataGenerator {
	private SkillHandler skillHandler;
	private GameHandler gameHandler;
	@Autowired
	public SkillGenerator(SkillHandler skillHandler, GameHandler gameHandler) {
		super();
		this.skillHandler = skillHandler;
		this.gameHandler = gameHandler;
	}
	@Override
	public void generate() {
		final String fun = "Fun to Play With";
		final String technical = "Good at Game";
		make(fun, null);
		make(technical, null);
		make("Participation", null);
		make("Coach", fun);
		make("Communicator", fun);
		make("Empower Team Decisions", fun);
		make("Family Friendly", fun);
		make("Interest in Others Success", fun);
		make("Listener", fun);
		make("Productive", technical);
		make("Roleplay", fun);
		make("Strategic", technical);
		List<Game> games = gameHandler.findAll();
		for(Game game : games) {
			make(game.getName(), technical);
		}
	}
	private void make(String name, String parentSkillName) {
		Skill skill = null;
		if((skill = skillHandler.findByName(name)) == null) {
			skill = new Skill();
		}
		skill.setName(name);
		Skill parentSkill = null;
		if(parentSkillName != null) {
			parentSkill = skillHandler.findByName(parentSkillName);
		}
		skill.setParentSkill(parentSkill);
		skillHandler.save(skill);
	}

}
