package com.revature.g2g.data.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.handlers.SkillHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillGenerator implements DataGenerator {
	private SkillHandler skillHandler;
	@Autowired
	public SkillGenerator(SkillHandler skillHandler) {
		super();
		this.skillHandler = skillHandler;
	}
	@Override
	public void generate() {
		final String fun = "Fun to Play With";
		final String technical = "Good at Game";
		make(fun, null);
		make(technical, null);
		make("Participation", null);
		make("Communicator", fun);
		make("Listener", fun);
		make("Coach", fun);
		make("Empower Team Decisions", fun);
		make("Interest in Others Success", fun);
		make("Productive", technical);
		make("Strategic", technical);
		make("Roleplay", fun);
		make("Family Friendly", fun);
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
