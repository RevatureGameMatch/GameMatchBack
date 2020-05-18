package com.revature.g2g.models;

public class SkillGameDTO {
	private long skillGameId;
	private SkillDTO skill;
	private GameDTO game;
	private int relevance;
	
	public SkillGameDTO(SkillGameJT skillGame) {
		super();
		this.skillGameId = skillGame.getSkillGameJTId();
		this.skill = new SkillDTO(skillGame.getSkill() );
		this.game = new GameDTO(skillGame.getGame() );
		this.relevance = skillGame.getRelevance();
	}

}
