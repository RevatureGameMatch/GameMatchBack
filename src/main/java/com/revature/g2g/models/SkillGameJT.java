package com.revature.g2g.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@Table(name = "G3G_SKILL_GAME_JT")
@Getter @Setter @EqualsAndHashCode @ToString
public class SkillGameJT implements Serializable {
	private static final long serialVersionUID = 1210666803036366164L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_game_jt_id")
	private long skillGameJTId;
	
	@NotNull(message = "SkillGameJT requires a skill.")
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@NotNull(message = "SkillGameJT requires a game.")
	@ManyToOne()
	@JoinColumn(name = "game_id")
	private Game game;
	
	@Column(name = "relevance")
	private int relevance;

	public SkillGameJT() {
		super();
		this.skill = new Skill();
		this.game = new Game();
	}
	
	public SkillGameJT(int skillGameJTId, Skill skill, Game game, int relevance) {
		super();
		this.skillGameJTId = skillGameJTId;
		this.skill = skill;
		this.game = game;
		this.relevance = relevance;
	}
	
	public SkillGameJT(SkillGameDTO source) {
		this();
		this.skillGameJTId = source.getSkillGameId();
		this.skill = new Skill(source.getSkill() );
		this.game = new Game(source.getGame() );
		this.relevance = source.getRelevance();
	}
}