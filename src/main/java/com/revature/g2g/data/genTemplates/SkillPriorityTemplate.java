package com.revature.g2g.data.genTemplates;

import java.util.Objects;

public class SkillPriorityTemplate {
	private String skill;
	private int priority;
	public SkillPriorityTemplate() {
		super();
	}
	public SkillPriorityTemplate(String skill, int priority) {
		super();
		this.skill = skill;
		this.priority = priority;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	@Override
	public int hashCode() {
		return Objects.hash(priority, skill);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SkillPriorityTemplate)) {
			return false;
		}
		SkillPriorityTemplate other = (SkillPriorityTemplate) obj;
		return priority == other.priority && Objects.equals(skill, other.skill);
	}
	@Override
	public String toString() {
		return "SkillPriorityTemplate [skill=" + skill + ", priority=" + priority + "]";
	}
}