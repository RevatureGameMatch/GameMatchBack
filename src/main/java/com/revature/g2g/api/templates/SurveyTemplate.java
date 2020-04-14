package com.revature.g2g.api.templates;

import java.util.Arrays;
import java.util.Objects;

public class SurveyTemplate {
	private PlayerTemplate playerTempalte;
	private SurveySkillTemplate[] skills;
	public SurveyTemplate() {
		super();
	}
	public SurveyTemplate(PlayerTemplate playerTempalte, SurveySkillTemplate[] skills) {
		super();
		this.playerTempalte = playerTempalte;
		this.skills = skills;
	}
	public PlayerTemplate getPlayerTempalte() {
		return playerTempalte;
	}
	public void setPlayerTempalte(PlayerTemplate playerTempalte) {
		this.playerTempalte = playerTempalte;
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
		result = prime * result + Objects.hash(playerTempalte);
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
		return Objects.equals(playerTempalte, other.playerTempalte) && Arrays.equals(skills, other.skills);
	}
	@Override
	public String toString() {
		return "SurveyTemplate [playerTempalte=" + playerTempalte + ", skills=" + Arrays.toString(skills) + "]";
	}
}