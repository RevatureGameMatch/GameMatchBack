package com.revature.g2g.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "changes" })
@Entity
@Table(name = "G2G_SKILL_PLAYER_JT")
public class SkillPlayerJT implements Serializable{
	private static final long serialVersionUID = 9019617057828732231L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_player_jt_id")
	private long skillPlayerJtId;
	
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@ManyToOne()
	@JoinColumn(name = "player_id")
	private Player player;
	
	@Column(name = "player_skill_value")
	private double value;
	
	@Column(name = "player_skill_exertise")
	private double expertise;

	@OneToMany(mappedBy = "skillPlayerJT", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> changes = new HashSet<>();

	public SkillPlayerJT() {
		super();
	}
	public SkillPlayerJT(long skillPlayerJtId, Skill skill, Player player, double value, double expertise) {
		super();
		this.skillPlayerJtId = skillPlayerJtId;
		this.skill = skill;
		this.player = player;
		this.value = value;
		this.expertise = expertise;
	}
	public long getSkillPlayerJtId() {
		return skillPlayerJtId;
	}
	public void setSkillPlayerJtId(long skillPlayerJtId) {
		this.skillPlayerJtId = skillPlayerJtId;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public double getExpertise() {
		return expertise;
	}
	public void setExpertise(double expertise) {
		this.expertise = expertise;
	}
	public Set<SkillPlayerChangeJT> getChanges() {
		return changes;
	}
	public void setChanges(Set<SkillPlayerChangeJT> changes) {
		this.changes = changes;
	}
	@Override
	public int hashCode() {
		return Objects.hash(expertise, player, skill, skillPlayerJtId, value);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SkillPlayerJT)) {
			return false;
		}
		SkillPlayerJT other = (SkillPlayerJT) obj;
		return Double.doubleToLongBits(expertise) == Double.doubleToLongBits(other.expertise)
				&& Objects.equals(player, other.player) && Objects.equals(skill, other.skill)
				&& skillPlayerJtId == other.skillPlayerJtId
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	@Override
	public String toString() {
		return "SkillPlayerJT [skillPlayerJtId=" + skillPlayerJtId + ", skill=" + skill + ", player=" + player
				+ ", value=" + value + ", expertise=" + expertise + "]";
	}
}