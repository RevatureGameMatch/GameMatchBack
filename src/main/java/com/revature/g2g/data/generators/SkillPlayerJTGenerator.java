package com.revature.g2g.data.generators;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerJTGenerator implements DataGenerator {
	private SkillHandler skillHandler;
	private PlayerHandler playerHandler;
	private SkillPlayerJTHandler skillPlayerJTHandler;
	private String[] skillNames;
	@Override
	public void generate() {
		make(playerHandler.findByUsername("Kayla"));
		make(playerHandler.findByUsername("Nancy"));
		make(playerHandler.findByUsername("Philip"));
	}
	@Autowired
	public SkillPlayerJTGenerator(SkillHandler skillHandler, PlayerHandler playerHandler,
			SkillPlayerJTHandler skillPlayerJTHandler) {
		super();
		this.skillHandler = skillHandler;
		this.playerHandler = playerHandler;
		this.skillPlayerJTHandler = skillPlayerJTHandler;
		this.skillNames = new String[] {"Participation", "Communicator", "Listner", "Coach", "Empower Team Decisions", "Interest in others Success", "Productive", "Fun to Play With", "Good at Game", "Strategic", "Roleplay"};
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
