package com.revature.g2g.api.templates;

import java.util.Objects;

import com.revature.g2g.models.Skill;

public class SurveySkillTemplate {
	private Skill skill;
	private float value;
	public SurveySkillTemplate() {
		super();
	}
	public SurveySkillTemplate(Skill skill, float value) {
		super();
		this.skill = skill;
		this.value = value;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(skill, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveySkillTemplate)) {
			return false;
		}
		SurveySkillTemplate other = (SurveySkillTemplate) obj;
		return Objects.equals(skill, other.skill) && Float.floatToIntBits(value) == Float.floatToIntBits(other.value);
	}
	@Override
	public String toString() {
		return "SurveySkillTemplate [skill=" + skill + ", value=" + value + "]";
	}
}