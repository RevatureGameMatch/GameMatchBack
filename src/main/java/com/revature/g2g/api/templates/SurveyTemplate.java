package com.revature.g2g.api.templates;

import java.util.Arrays;
import java.util.Objects;

public class SurveyTemplate {
	private PlayerTemplate playerTemplate;
	private SurveySkillTemplate[] skills;
	public SurveyTemplate() {
		super();
	}
	public SurveyTemplate(PlayerTemplate playerTemplate, SurveySkillTemplate[] skills) {
		super();
		this.playerTemplate = playerTemplate;
		this.skills = skills;
	}
	public PlayerTemplate getPlayerTemplate() {
		return playerTemplate;
	}
	public void setPlayerTemplate(PlayerTemplate playerTemplate) {
		this.playerTemplate = playerTemplate;
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
		result = prime * result + Objects.hash(playerTemplate);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyTemplate other = (SurveyTemplate) obj;
		return Objects.equals(playerTemplate, other.playerTemplate) && Arrays.equals(skills, other.skills);
	}
	@Override
	public String toString() {
		return "SurveyTemplate [playerTemplate=" + playerTemplate + ", skills=" + Arrays.toString(skills) + "]";
	}
}