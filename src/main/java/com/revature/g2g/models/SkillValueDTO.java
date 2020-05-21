package com.revature.g2g.models;

import java.util.Objects;

import javax.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SkillValueDTO {
	private SkillDTO skill;
	
	@DecimalMin(value="0.01")
	private double value;
	
	public SkillValueDTO(SkillDTO skill, double value) {
		super();
		this.skill = skill;
		this.value = value;
	}

	public void setSkill(SkillDTO skill) {
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