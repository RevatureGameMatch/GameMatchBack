package com.revature.g2g.api.templates;

import java.util.Objects;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;

public class SurveySkillTemplate {
	private Skill skill;
	private float value;
	private Game game;
	public SurveySkillTemplate() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SurveySkillTemplate(Skill skill, float value, Game game) {
		super();
		this.skill = skill;
		this.value = value;
		this.game = game;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	@Override
	public int hashCode() {
		return Objects.hash(game, skill, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveySkillTemplate other = (SurveySkillTemplate) obj;
		return Objects.equals(game, other.game) && Objects.equals(skill, other.skill)
				&& Float.floatToIntBits(value) == Float.floatToIntBits(other.value);
	}
	@Override
	public String toString() {
		return "SurveySkillTemplate [skill=" + skill + ", value=" + value + ", game=" + game + "]";
	}
	
}