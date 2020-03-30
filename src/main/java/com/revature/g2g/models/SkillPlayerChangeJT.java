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
@Table(name = "G2G_SKILL_PLAYER_CHANGE_JT")
public class SkillPlayerChangeJT {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "skill_player_change_jt")
	private int skillPlayerChangeJTId;
	
	@ManyToOne()
	@JoinColumn(name = "skill_id")
	private SkillPlayerJT skillPlayerJT;
	
	@ManyToOne
	@JoinColumn(name = "player_id")
	private Player player;
	
	@ManyToOne
	@JoinColumn(name = "modified_by_id")
	private Player modifiedBy;
	
	@Column(name = "modified_by_expertise")
	private double expertise;
	
	@ManyToOne
	@JoinColumn(name = "room_id")
	private Room roomId;
	
	@Column(name = "value")
	private double value;

	public SkillPlayerChangeJT() {
		super();
	}
	public SkillPlayerChangeJT(int skillPlayerChangeJTId, SkillPlayerJT skillPlayerJT, Player player, Player modifiedBy,
			double expertise, Room roomId, double value) {
		super();
		this.skillPlayerChangeJTId = skillPlayerChangeJTId;
		this.skillPlayerJT = skillPlayerJT;
		this.player = player;
		this.modifiedBy = modifiedBy;
		this.expertise = expertise;
		this.roomId = roomId;
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
	public void setExpertise(double expertise) {
		this.expertise = expertise;
	}
	public Room getRoomId() {
		return roomId;
	}
	public void setRoomId(Room roomId) {
		this.roomId = roomId;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(expertise);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((modifiedBy == null) ? 0 : modifiedBy.hashCode());
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + ((roomId == null) ? 0 : roomId.hashCode());
		result = prime * result + skillPlayerChangeJTId;
		result = prime * result + ((skillPlayerJT == null) ? 0 : skillPlayerJT.hashCode());
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
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
		if (Double.doubleToLongBits(expertise) != Double.doubleToLongBits(other.expertise)) {
			return false;
		}
		if (modifiedBy == null) {
			if (other.modifiedBy != null) {
				return false;
			}
		} else if (!modifiedBy.equals(other.modifiedBy)) {
			return false;
		}
		if (player == null) {
			if (other.player != null) {
				return false;
			}
		} else if (!player.equals(other.player)) {
			return false;
		}
		if (roomId == null) {
			if (other.roomId != null) {
				return false;
			}
		} else if (!roomId.equals(other.roomId)) {
			return false;
		}
		if (skillPlayerChangeJTId != other.skillPlayerChangeJTId) {
			return false;
		}
		if (skillPlayerJT == null) {
			if (other.skillPlayerJT != null) {
				return false;
			}
		} else if (!skillPlayerJT.equals(other.skillPlayerJT)) {
			return false;
		}
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "SkillPlayerChangeJT [skillPlayerChangeJTId=" + skillPlayerChangeJTId + ", skillPlayerJT="
				+ skillPlayerJT + ", player=" + player + ", modifiedBy=" + modifiedBy + ", expertise=" + expertise
				+ ", roomId=" + roomId + ", value=" + value + "]";
	}
}