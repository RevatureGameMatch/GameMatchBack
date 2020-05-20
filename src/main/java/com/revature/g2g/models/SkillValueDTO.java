package com.revature.g2g.models;

import java.util.Objects;

public class SkillValueDTO {
	private Skill skill;
	private double value;
	public SkillValueDTO() {
		super();
	}
	public SkillValueDTO(Skill skill, double value) {
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
		if (!(obj instanceof SkillValueDTO)) {
			return false;
		}
		SkillValueDTO other = (SkillValueDTO) obj;
		return Objects.equals(skill, other.skill)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	@Override
	public String toString() {
		return "SkillValueTemplate [skill=" + skill + ", value=" + value + "]";
	}
}