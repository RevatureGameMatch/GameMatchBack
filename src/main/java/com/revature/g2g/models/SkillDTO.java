package com.revature.g2g.models;

public class SkillDTO {
	private long skillId;
	private String name;
	private SkillDTO parentSkill;

	public SkillDTO(Skill skill) {
		super();
		this.skillId = skill.getSkillId();
		this.name = skill.getName();
		this.parentSkill = new SkillDTO(skill.getParentSkill() );
	}
}
