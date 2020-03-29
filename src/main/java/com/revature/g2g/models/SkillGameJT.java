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

@Entity
@Table(name = "G2G_SKILL_GAME_JT")
public class SkillGameJT implements Serializable {
	private static final long serialVersionUID = 1210666803036366164L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_game_jt_id")
	private int gameSkillJTId;
	
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@ManyToOne()
	@JoinColumn(name = "game_id")
	private Game game;
	
	@Column(name = "relevance")
	private int relevance;

	public SkillGameJT() {
		super();
	}
	public SkillGameJT(int gameSkillJTId, Skill skill, Game game, int relevance) {
		super();
		this.gameSkillJTId = gameSkillJTId;
		this.skill = skill;
		this.game = game;
		this.relevance = relevance;
	}
	public int getGameSkillJTId() {
		return gameSkillJTId;
	}
	public void setGameSkillJTId(int gameSkillJTId) {
		this.gameSkillJTId = gameSkillJTId;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public int getRelevance() {
		return relevance;
	}
	public void setRelevance(int relevance) {
		this.relevance = relevance;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((game == null) ? 0 : game.hashCode());
		result = prime * result + gameSkillJTId;
		result = prime * result + relevance;
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SkillGameJT)) {
			return false;
		}
		SkillGameJT other = (SkillGameJT) obj;
		if (game == null) {
			if (other.game != null) {
				return false;
			}
		} else if (!game.equals(other.game)) {
			return false;
		}
		if (gameSkillJTId != other.gameSkillJTId) {
			return false;
		}
		if (relevance != other.relevance) {
			return false;
		}
		if (skill == null) {
			if (other.skill != null) {
				return false;
			}
		} else if (!skill.equals(other.skill)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "SkillGameJT [gameSkillJTId=" + gameSkillJTId + ", skill=" + skill + ", game=" + game + ", relevance="
				+ relevance + "]";
	}
}