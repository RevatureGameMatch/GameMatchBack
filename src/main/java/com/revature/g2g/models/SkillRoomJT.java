package com.revature.g2g.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "G2G_SKILL_ROOM_JT")
public class SkillRoomJT {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_room_jt_id")
	private int skillRoomJTId;
	
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private Skill skill;
	
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
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(minValue);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((skill == null) ? 0 : skill.hashCode());
		result = prime * result + skillRoomJTId;
		return result;
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
		if (Double.doubleToLongBits(minValue) != Double.doubleToLongBits(other.minValue)) {
			return false;
		}
		if (room == null) {
			if (other.room != null) {
				return false;
			}
		} else if (!room.equals(other.room)) {
			return false;
		}
		if (skill == null) {
			if (other.skill != null) {
				return false;
			}
		} else if (!skill.equals(other.skill)) {
			return false;
		}
		if (skillRoomJTId != other.skillRoomJTId) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "SkillRoomJT [skillRoomJTId=" + skillRoomJTId + ", skill=" + skill + ", room=" + room + ", minValue="
				+ minValue + "]";
	}
}