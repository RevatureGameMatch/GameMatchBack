package com.revature.g2g.services.helpers;

import org.springframework.stereotype.Service;

import com.revature.g2g.models.SkillPlayerJT;

@Service
public class BalanceHelper {
	/**
	 * Checks a proposed change against maximum and minimum values correcting illegal values
	 * @param float skillChange
	 * @return float skillChange
	 */
	public float limitChange(float value) {
		float result;
		if (value > BalanceConstants.getMaxGain()) {result = BalanceConstants.getMaxGain();}
		else if (value < BalanceConstants.getMaxLoss()) {result = BalanceConstants.getMaxLoss();}
		else {result = value;}
		return result;
	}
	/**
	 * Calculates a players expertise in a skill.
	 * @param SkillPlayerJT skill
	 * @return float expertise
	 */
	public float calculateExpertise(SkillPlayerJT skill) {
		return (float) (skill.getValue() / BalanceConstants.getMaxValue());
	}
	/**
	 * Checks a proposed new skill value against maximum and minimum values correcting illegal values
	 * @param float skillValue
	 * @return float skillValue
	 */
	public float limitValue(float value) {
		float result;
		if (value > BalanceConstants.getMaxValue()) { result = BalanceConstants.getMaxValue(); }
		else if (value < BalanceConstants.getMinValue()) { result = BalanceConstants.getMinValue(); }
		else {result = value;}
		return result;
	}
	/**
	 * Calculates the change for a skill value.
	 * @param float changeDirection "1" or "-1" based on positive or negative review
	 * @param float modifiedByExpertise "0" to "1" based on the expertise of the player voting
	 * @param long gameTime the duration a game lasted
	 * @return float change to a players skill value
	 */
	public float calculateChange(float changeDirection, float modifiedByExpertise, long gameTime) {
		float change = changeDirection * modifiedByExpertise * (((float) gameTime)/BalanceConstants.getDefaultGameTime()) * BalanceConstants.getMaxGain();
		return limitValue(change);
	}
}