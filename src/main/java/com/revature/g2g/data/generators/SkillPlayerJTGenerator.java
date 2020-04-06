package com.revature.g2g.data.generators;

import java.util.Random;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;

public class SkillPlayerJTGenerator implements DataGenerator {
	private SkillHandler skillHandler = new SkillHandler();
	private PlayerHandler playerHandler = new PlayerHandler();
	private SkillPlayerJTHandler skillPlayerJTHandler = new SkillPlayerJTHandler();
	private String[] skillNames = new String[] {"Participation", "Communicator", "Listner", "Coach", "Empower Team Decisions", "Interest in others Success", "Productive", "Fun to Play With", "Good at Game", "Strategic", "Roleplay"};

	@Override
	public void generate() {
		make(playerHandler.findByUsername("Kayla"));
		make(playerHandler.findByUsername("Nancy"));
		make(playerHandler.findByUsername("Philip"));
	}
	private void make(Player player){
		if(player != null) {
			for (String skillName : skillNames) {
				Skill skill = skillHandler.findByName(skillName);
				if (skill == null)continue;
				SkillPlayerJT skillPlayerJT = null;
				boolean preExistant = true;
				if((skillPlayerJT = skillPlayerJTHandler.findBySkillPlayer(skill, player)) == null) {
					preExistant = false;
					skillPlayerJT = new SkillPlayerJT();
					skillPlayerJT.setPlayer(player);
					skillPlayerJT.setSkill(skill);
				}
				skillPlayerJT.setExpertise(new Random().nextDouble());
				skillPlayerJT.setValue(new Random().nextDouble() * 100d);
				if(preExistant) {
					skillPlayerJTHandler.update(skillPlayerJT);
				}else {
					skillPlayerJTHandler.insert(skillPlayerJT);
				}
			}
		}
	}

}
