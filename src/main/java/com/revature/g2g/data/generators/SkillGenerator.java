package com.revature.g2g.data.generators;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.handlers.SkillHandler;

public class SkillGenerator implements DataGenerator {
	private static SkillHandler skillHandler = new SkillHandler();
	@Override
	public void generate() {
		final String fun = "Fun to Play With";
		final String technical = "Good at Game";
		make(fun, null);
		make(technical, null);
		make("Participation", null);
		make("Communicator", fun);
		make("Listner", fun);
		make("Coach", fun);
		make("Empower Team Decisions", fun);
		make("Interest in others Success", fun);
		make("Productive", technical);
		make("Strategic", technical);
	}
	private void make(String name, String parentSkillName) {
		Skill skill = null;
		boolean preExistant = false;
		if((skill = skillHandler.findByName(name)) != null) {
			preExistant = true;
		}else {
			skill = new Skill();
		}
		skill.setName(name);
		Skill parentSkill = null;
		if(parentSkillName != null) {
			parentSkill = skillHandler.findByName(parentSkillName);
		}
		skill.setParentSkill(parentSkill);
		if(preExistant) {
			skillHandler.update(skill);
		}else {
			skillHandler.insert(skill);
		}
	}

}
