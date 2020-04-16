package com.revature.g2g.models;

import java.io.Serializable;
import java.util.Objects;

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

@Component
@Entity
@Table(name = "G2G_SKILL_PLAYER_CHANGE_JT")
public class SkillPlayerChangeJT implements Serializable{
	private static final long serialVersionUID = -253737794481944410L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_player_change_jt")
	private int skillPlayerChangeJTId;
	
	@NotNull(message = "SkillPlayerChangeJT requires a SkillPlayerJT")
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private SkillPlayerJT skillPlayerJT;
	
	@NotNull(message = "SkillPlayerChangeJT requires a Changed Player.")
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@NotNull(message = "SkillPlayerChangeJT requires a Modified Player")
	@ManyToOne
	@JoinColumn(name = "modified_by_id")
	private Player modifiedBy;
	
	@Column(name = "modified_by_expertise")
	private float expertise;
	
	@NotNull(message = "SkillPlayerChangeJT requires a Room.")
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "value")
	private float value;

	public SkillPlayerChangeJT() {
		super();
		this.skillPlayerJT = new SkillPlayerJT();
		this.player = new Player();
		this.modifiedBy = new Player();
		this.room = new Room();
	}
	public SkillPlayerChangeJT(int skillPlayerChangeJTId, SkillPlayerJT skillPlayerJT, Player player, Player modifiedBy,
			float expertise, Room room, float value) {
		super();
		this.skillPlayerChangeJTId = skillPlayerChangeJTId;
		this.skillPlayerJT = skillPlayerJT;
		this.player = player;
		this.modifiedBy = modifiedBy;
		this.expertise = expertise;
		this.room = room;
		this.value = value;
	}
	public int getSkillPlayerChangeJTId() {
		return skillPlayerChangeJTId;
	}
	public void setSkillPlayerChangeJTId(int skillPlayerChangeJTId) {
		this.skillPlayerChangeJTId = skillPlayerChangeJTId;
	}
	public SkillPlayerJT getSkillPlayerJT() {
		return skillPlayerJT;
	}
	public void setSkillPlayerJT(SkillPlayerJT skillPlayerJT) {
		this.skillPlayerJT = skillPlayerJT;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Player getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Player modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public double getExpertise() {
		return expertise;
	}
	public void setExpertise(float expertise) {
		this.expertise = expertise;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public double getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		return Objects.hash(modifiedBy, player, room, skillPlayerJT);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SkillPlayerChangeJT)) {
			return false;
		}
		SkillPlayerChangeJT other = (SkillPlayerChangeJT) obj;
		return Objects.equals(modifiedBy, other.modifiedBy) && Objects.equals(player, other.player)
				&& Objects.equals(room, other.room) && Objects.equals(skillPlayerJT, other.skillPlayerJT);
	}
	@Override
	public String toString() {
		return "SkillPlayerChangeJT [skillPlayerChangeJTId=" + skillPlayerChangeJTId + ", skillPlayerJT="
				+ skillPlayerJT + ", player=" + player + ", modifiedBy=" + modifiedBy + ", expertise=" + expertise
				+ ", room=" + room + ", value=" + value + "]";
	}
}