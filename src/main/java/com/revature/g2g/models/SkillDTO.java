package com.revature.g2g.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SkillDTO {
	private long skillId;
	private String name;
	private SkillDTO parentSkill;

	public SkillDTO(Skill skill) {
		super();
		this.skillId = skill.getSkillId();
		this.name = skill.getName();
		this.parentSkill = new SkillDTO(skill.getParentSkill(), true );
	}
	
	public SkillDTO(Skill skill, boolean stopParentSkill){ 
		super(); 
		this.skillId = skill.getSkillId(); 
		this.name = skill.getName(); 
		if (stopParentSkill){ 
			this.parentSkill = new SkillDTO(skill.getParentSkill(), false); 
		} else{ 
			this.parentSkill = null; 
		} 
	}

	public SkillDTO(SkillDTO skill) {
		super(); 
		this.skillId = skill.getSkillId(); 
		this.name = skill.getName(); 
			this.parentSkill = new SkillDTO(skill.getParentSkill() ); 
	}
	
}
