package com.revature.g2g.services.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
import com.revature.g2g.services.helpers.BalanceConstants;
import com.revature.g2g.services.helpers.BalanceHelper;

@Service
public class SkillPlayerJTService {
	BalanceHelper balanceHelper;
	SkillPlayerJTHandler skillPlayerJTHandler;
	SkillHandler skillHandler;
	GameHandler gameHandler;
	SkillGameJTHandler skillGameJTHandler;
	@Autowired
	public SkillPlayerJTService(BalanceHelper balanceHelper, SkillPlayerJTHandler skillPlayerJTHandler,
			SkillHandler skillHandler, GameHandler gameHandler, SkillGameJTHandler skillGameJTHandler) {
		super();
		this.balanceHelper = balanceHelper;
		this.skillPlayerJTHandler = skillPlayerJTHandler;
		this.skillHandler = skillHandler;
		this.gameHandler = gameHandler;
	}
	/**
	 * Takes a skill and adds it to a player with the default level.
	 * @param skill
	 * @param player
	 * @return
	 */
	public SkillPlayerJT addSkillWithDefaultValue(Skill skill, Player player) {
		SkillPlayerJT skillPlayerJT = new SkillPlayerJT();
		skillPlayerJT.setPlayer(player);
		skillPlayerJT.setSkill(skill);
		skillPlayerJT.setValue(BalanceConstants.getStartValue());
		skillPlayerJT.setExpertise(balanceHelper.calculateExpertise(skillPlayerJT));
		skillPlayerJTHandler.save(skillPlayerJT);
		return skillPlayerJT;
	}
	/**
	 * Adds all default skills to a player
	 * @param player
	 */
	public void addDefaultSkills(Player player) {
		String[] defaultSkills = BalanceConstants.getDefaultSkills();
		for (String skillName : defaultSkills) {
			Skill skill = skillHandler.findByName(skillName);
			if(skill  == null) continue;
			addSkillWithDefaultValue(skill, player);
		}
	}
	/**
	 * Checks a player for each skill relevant to a game, adding any
	 * that they do not already have
	 * @param player
	 * @param game
	 */
	public void checkThenAddGamesSkills(Player player, Game game) {
		List<SkillGameJT> skillGameJTs = skillGameJTHandler.findJTByGame(game);
		for (SkillGameJT skillGameJT : skillGameJTs) {
			SkillPlayerJT preExistingSkill = skillPlayerJTHandler.findBySkillPlayer(skillGameJT.getSkill(), player);
			if (preExistingSkill != null) continue;
			addSkillWithDefaultValue(skillGameJT.getSkill(), player);
		}
	}
}