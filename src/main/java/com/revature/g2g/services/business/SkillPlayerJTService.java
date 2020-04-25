package com.revature.g2g.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
import com.revature.g2g.services.helpers.BalanceConstants;
import com.revature.g2g.services.helpers.BalanceHelper;

@Service
public class SkillPlayerJTService {
	BalanceHelper balanceHelper;
	SkillPlayerJTHandler skillPlayerJTHandler;
	@Autowired
	public SkillPlayerJTService(BalanceHelper balanceHelper, SkillPlayerJTHandler skillPlayerJTHandler) {
		super();
		this.balanceHelper = balanceHelper;
		this.skillPlayerJTHandler = skillPlayerJTHandler;
	}
	public SkillPlayerJT makeDefault(Skill skill, Player player) {
		SkillPlayerJT skillPlayerJT = new SkillPlayerJT();
		skillPlayerJT.setPlayer(player);
		skillPlayerJT.setSkill(skill);
		skillPlayerJT.setValue(BalanceConstants.getStartValue());
		skillPlayerJT.setExpertise(balanceHelper.calculateExpertise(skillPlayerJT));
		skillPlayerJTHandler.save(skillPlayerJT);
		return skillPlayerJT;
	}
}