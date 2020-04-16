package com.revature.g2g.api.templates;

import java.util.Objects;

import com.revature.g2g.models.Skill;

public class SkillValueTemplate {
	private Skill skill;
	private double value;
	public SkillValueTemplate() {
		super();
	}
	public SkillValueTemplate(Skill skill, double value) {
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
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
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
		if (!(obj instanceof SkillValueTemplate)) {
			return false;
		}
		SkillValueTemplate other = (SkillValueTemplate) obj;
		return Objects.equals(skill, other.skill)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	@Override
	public String toString() {
		return "SkillValueTemplate [skill=" + skill + ", value=" + value + "]";
	}
}