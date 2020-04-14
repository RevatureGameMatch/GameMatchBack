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
	@Autowired
	private SkillPlayerJTHandler skillPlayerJTHandler;
	@Autowired
	private BalanceHelper balanceHelper;
	@Autowired
	private SkillPlayerJTService skillPlayerJTService;
	@Autowired
	private SkillPlayerChangeJTHandler skillPlayerChangeJTHandler;
	@Autowired
	private LoggerSingleton loggerSingleton;
	@Autowired 
	public void submit(Player modifiedBy, Player player, Room room, Skill skill, float value) {
		//Setup
		SkillPlayerJT modifiedBySkill = skillPlayerJTHandler.findBySkillPlayer(skill, modifiedBy);
		float modifiedByExpertise = modifiedBySkill == null ? BalanceConstants.getMinExpertise() : balanceHelper.calculateExpertise(modifiedBySkill);
		SkillPlayerJT skillPlayerJT = skillPlayerJTHandler.findBySkillPlayer(skill, player);
		if(skillPlayerJT == null) {
			skillPlayerJT = skillPlayerJTService.makeDefault(skill, player);
		}
		
		//Make record and log
		SkillPlayerChangeJT skillPlayerChangeJT = new SkillPlayerChangeJT();
		skillPlayerChangeJT.setExpertise(modifiedByExpertise);
		skillPlayerChangeJT.setModifiedBy(modifiedBy);
		skillPlayerChangeJT.setPlayer(player);
		skillPlayerChangeJT.setRoom(room);
		skillPlayerChangeJT.setSkillPlayerJT(skillPlayerJT);
		skillPlayerChangeJTHandler.insert(skillPlayerChangeJT);
		String logMessage = skillPlayerChangeJT.toString();
		loggerSingleton.getBusinessLog().trace(logMessage);
		
		//Modify the stored skillJT
	}
}