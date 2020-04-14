package com.revature.g2g.api.templates;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;

public class SurveySubmitTemplate {
	@NotNull(message="SurveySubmitTemplate requires a player")
	private Player player;
	@NotNull(message="SurveySubmitTemplate requires a modifiedBy")
	private PlayerTemplate modifiedBy;
	@NotNull(message="SurveySubmitTemplate requires a skill")
	private Skill skill;
	private float value;
	public SurveySubmitTemplate() {
		super();
	}
	public Player getPlayer() {
		return player;
	}
	public SurveySubmitTemplate(Player player, PlayerTemplate modifiedBy, Skill skill, float value) {
		super();
		this.player = player;
		this.modifiedBy = modifiedBy;
		this.skill = skill;
		this.value = value;
	}
	public void setPlayer(Player player) {
		this.player = player;
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
	public PlayerTemplate getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(PlayerTemplate modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public int hashCode() {
		return Objects.hash(modifiedBy, player, skill, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveySubmitTemplate)) {
			return false;
		}
		SurveySubmitTemplate other = (SurveySubmitTemplate) obj;
		return Objects.equals(modifiedBy, other.modifiedBy) && Objects.equals(player, other.player)
				&& Objects.equals(skill, other.skill)
				&& Float.floatToIntBits(value) == Float.floatToIntBits(other.value);
	}
	@Override
	public String toString() {
		return "SurveySubmitTemplate [player=" + player + ", modifiedBy=" + modifiedBy + ", skill=" + skill + ", value="
				+ value + "]";
	}
}