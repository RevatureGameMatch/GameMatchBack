package com.revature.g2g.models;

import java.io.Serializable;
import java.util.HashSet;
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

	@OneToMany(mappedBy = "skill_id", fetch = FetchType.LAZY)
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
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(expertise);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + (int) (skillPlayerJtId  ^ (skillPlayerJtId  >>> 32));
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
		if (Double.doubleToLongBits(expertise) != Double.doubleToLongBits(other.expertise)) {
			return false;
		}
		if (player == null) {
			if (other.player != null) {
				return false;
			}
		} else if (!player.equals(other.player)) {
			return false;
		}
		if (skill == null) {
			if (other.skill != null) {
				return false;
			}
		} else if (!skill.equals(other.skill)) {
			return false;
		}
		if (skillPlayerJtId != other.skillPlayerJtId) {
			return false;
		}
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "SkillPlayerJT [skillPlayerJtId=" + skillPlayerJtId + ", skill=" + skill + ", player=" + player
				+ ", value=" + value + ", expertise=" + expertise + "]";
	}
}