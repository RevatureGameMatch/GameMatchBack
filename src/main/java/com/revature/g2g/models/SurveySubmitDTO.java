package com.revature.g2g.models;

import lombok.Data;

@Data
public class SurveySubmitDTO {
	private PlayerDTO player;
	private PlayerDTO modifiedBy;
	private SkillDTO skill;
	
	private float value;

	public SurveySubmitDTO() {
		super();
		this.player = new PlayerDTO();
		this.modifiedBy = new PlayerDTO();
		this.skill = new SkillDTO();
	}
	
	public SurveySubmitDTO(PlayerDTO player, PlayerDTO modifiedBy, SkillDTO skill, float value) {
		super();
		this.player = new PlayerDTO(player);
		this.modifiedBy = new PlayerDTO(modifiedBy);
		this.skill = new SkillDTO(skill);
		this.value = value;
	}

}
