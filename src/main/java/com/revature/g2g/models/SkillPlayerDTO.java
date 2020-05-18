package com.revature.g2g.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SkillPlayerDTO {
	private long skillPlayerId;
	private SkillDTO skill;
	private PlayerDTO player;
	private double value;
	private double expertise;

	public SkillPlayerDTO(SkillPlayerJT source) {
		super();
		this.skillPlayerId = source.getSkillPlayerJtId();
		this.skill = new SkillDTO(source.getSkill() );
		this.player = new PlayerDTO(source.getPlayer() );
		this.value = source.getValue();
		this.expertise = source.getExpertise();
	}

}
