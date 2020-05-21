package com.revature.g2g.models;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SkillGameDTO {
	@Positive
	private long skillGameId;
	
	private SkillDTO skill;
	private GameDTO game;
	
	@PositiveOrZero
	private int relevance;
	
	public SkillGameDTO(SkillGameJT skillGame) {
		super();
		this.skillGameId = skillGame.getSkillGameJTId();
		this.skill = new SkillDTO(skillGame.getSkill() );
		this.game = new GameDTO(skillGame.getGame() );
		this.relevance = skillGame.getRelevance();
	}

}
