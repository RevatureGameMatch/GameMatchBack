package com.revature.g2g.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SurveySkillDTO {
	private SkillDTO skill;
	private double value;
	private GameDTO game;

	public SurveySkillDTO(SkillDTO s, double d, GameDTO game) {
		super();
		this.skill = s;
		this.value = d;
		this.game = game;
	}
}
