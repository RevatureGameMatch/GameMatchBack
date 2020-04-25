package com.revature.g2g.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.services.handlers.SkillPlayerChangeJTHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
import com.revature.g2g.services.helpers.BalanceConstants;
import com.revature.g2g.services.helpers.BalanceHelper;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Service
public class SurveyService {
	private SkillPlayerJTHandler skillPlayerJTHandler;
	private BalanceHelper balanceHelper;
	private SkillPlayerJTService skillPlayerJTService;
	private SkillPlayerChangeJTHandler skillPlayerChangeJTHandler;
	private LoggerSingleton loggerSingleton;
	@Autowired
	public SurveyService(SkillPlayerJTHandler skillPlayerJTHandler, BalanceHelper balanceHelper,
			SkillPlayerJTService skillPlayerJTService, SkillPlayerChangeJTHandler skillPlayerChangeJTHandler,
			LoggerSingleton loggerSingleton) {
		super();
		this.skillPlayerJTHandler = skillPlayerJTHandler;
		this.balanceHelper = balanceHelper;
		this.skillPlayerJTService = skillPlayerJTService;
		this.skillPlayerChangeJTHandler = skillPlayerChangeJTHandler;
		this.loggerSingleton = loggerSingleton;
	}
	public SkillPlayerChangeJT submit(Player modifiedBy, Player player, Room room, Skill skill, float value) {
		//Setup
		SkillPlayerJT modifiedBySkill = skillPlayerJTHandler.findBySkillPlayer(skill, modifiedBy);
		float modifiedByExpertise = modifiedBySkill == null ? BalanceConstants.getMinExpertise() : balanceHelper.calculateExpertise(modifiedBySkill);
		SkillPlayerJT skillPlayerJT = skillPlayerJTHandler.findBySkillPlayer(skill, player);
		if(skillPlayerJT == null) {
			skillPlayerJT = skillPlayerJTService.makeDefault(skill, player);
		}
		SkillPlayerChangeJT skillPlayerChangeJT = new SkillPlayerChangeJT();
		long gameTime = 0;
		//Modify the stored skillJT
		if (room.getClosed() == null) {
			gameTime = (BalanceConstants.getDefaultGameTime())/2;
		} else {
			gameTime = room.getClosed().getTime() - room.getCreated().getTime();
		}
		float changeDirection = 0;
		if(value > 0) { changeDirection = 1;}
		else if(value < 0) { changeDirection = -1;}
		float change = balanceHelper.calculateChange(changeDirection, modifiedByExpertise, gameTime);
		float newValue = (float) (skillPlayerJT.getValue() + change);
		newValue = balanceHelper.limitValue(newValue);
		skillPlayerJT.setValue(newValue);
		skillPlayerJTHandler.save(skillPlayerJT);
		
		//Make record and log
		skillPlayerChangeJT.setExpertise(modifiedByExpertise);
		skillPlayerChangeJT.setModifiedBy(modifiedBy);
		skillPlayerChangeJT.setPlayer(player);
		skillPlayerChangeJT.setRoom(room);
		skillPlayerChangeJT.setSkillPlayerJT(skillPlayerJT);
		skillPlayerChangeJT.setValue(change);
		skillPlayerChangeJTHandler.save(skillPlayerChangeJT);
		String logMessage = "SurveyService: adding change to records " + skillPlayerChangeJT.toString();
		loggerSingleton.getBusinessLog().trace(logMessage);
		return skillPlayerChangeJT;
	}
}