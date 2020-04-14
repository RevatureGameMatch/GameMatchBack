package com.revature.g2g.services.helpers;

import org.springframework.stereotype.Service;

import com.revature.g2g.models.SkillPlayerJT;

@Service
public class BalanceHelper {
	public float limitChange(float value) {
		float result;
		if (value > BalanceConstants.getMaxGain()) {result = BalanceConstants.getMaxGain();}
		else if (value < BalanceConstants.getMaxLoss()) {result = BalanceConstants.getMaxLoss();}
		else {result = value;}
		return result;
	}
	public float calculateExpertise(SkillPlayerJT skill) {
		return (float) (skill.getValue() / BalanceConstants.getMaxValue());
	}
}