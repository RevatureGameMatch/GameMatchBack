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
@Table(name = "G2G_SKILL_ROOM_JT")
public class SkillRoomJT implements Serializable{
	private static final long serialVersionUID = 3217583926494781567L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_room_jt_id")
	private int skillRoomJTId;
	
	@NotNull(message = "SkillRoomJT requires a skill.")
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
	@NotNull(message = "Room requires a room.")
	@ManyToOne()
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "min_value")
	private double minValue;

	public SkillRoomJT() {
		super();
	}
	public SkillRoomJT(int skillRoomJTId, Skill skill, Room room, double minValue) {
		super();
		this.skillRoomJTId = skillRoomJTId;
		this.skill = skill;
		this.room = room;
		this.minValue = minValue;
	}
	public int getSkillRoomJTId() {
		return skillRoomJTId;
	}
	public void setSkillRoomJTId(int skillRoomJTId) {
		this.skillRoomJTId = skillRoomJTId;
	}
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public double getMinValue() {
		return minValue;
	}
	public void setMinValue(double minValue) {
		this.minValue = minValue;
	}
	@Override
	public int hashCode() {
		return Objects.hash(minValue, room, skill, skillRoomJTId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SkillRoomJT)) {
			return false;
		}
		SkillRoomJT other = (SkillRoomJT) obj;
		return Double.doubleToLongBits(minValue) == Double.doubleToLongBits(other.minValue)
				&& Objects.equals(room, other.room) && Objects.equals(skill, other.skill)
				&& skillRoomJTId == other.skillRoomJTId;
	}
	@Override
	public String toString() {
		return "SkillRoomJT [skillRoomJTId=" + skillRoomJTId + ", skill=" + skill + ", room=" + room + ", minValue="
				+ minValue + "]";
	}
}