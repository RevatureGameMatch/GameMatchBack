package com.revature.g2g.api.templates;

import java.util.Arrays;
import java.util.Objects;

import com.revature.g2g.models.Player;

public class SurveyTemplate {
	private Player player;
	private SurveySkillTemplate[] skills;
	public SurveyTemplate() {
		super();
	}
	public SurveyTemplate(Player player, SurveySkillTemplate[] skills) {
		super();
		this.player = player;
		this.skills = skills;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public SurveySkillTemplate[] getSkills() {
		return skills;
	}
	public void setSkills(SurveySkillTemplate[] skills) {
		this.skills = skills;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(skills);
		result = prime * result + Objects.hash(player);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveyTemplate)) {
			return false;
		}
		SurveyTemplate other = (SurveyTemplate) obj;
		return Objects.equals(player, other.player) && Arrays.equals(skills, other.skills);
	}
	@Override
	public String toString() {
		return "SurveyTemplate [player=" + player + ", skills=" + Arrays.toString(skills) + "]";
	}
}