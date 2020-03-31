package com.revature.g2g.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "G2G_ROOM")
public class Room implements Serializable{
	private static final long serialVersionUID = -5444471863298926133L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name = "associated_discord_room")
	private String associatedDiscordRoom;
	
	@Column(name = "room_created")
	private Date created;
	
	@Column(name = "room_closed")
	private Date closed;
	
	@Column(name = "room_description")
	private String description;
	
	@Column(name = "room_status")
	private RoomStatus status;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private Set<PlayerRoomJT> playerJT = new HashSet<>();

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> roomChangesToSkill = new HashSet<>();

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private Set<SkillRoomJT> skills = new HashSet<>();

	public Room() {
		super();
	}
	public Room(int roomId, String associatedDiscordRoom, Date created, Date closed, String description,
			RoomStatus status) {
		super();
		this.roomId = roomId;
		this.associatedDiscordRoom = associatedDiscordRoom;
		this.created = created;
		this.closed = closed;
		this.description = description;
		this.status = status;
	}
	public int getRoomId() {
		return roomId;
	}
	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}
	public String getAssociatedDiscordRoom() {
		return associatedDiscordRoom;
	}
	public void setAssociatedDiscordRoom(String associatedDiscordRoom) {
		this.associatedDiscordRoom = associatedDiscordRoom;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getClosed() {
		return closed;
	}
	public void setClosed(Date closed) {
		this.closed = closed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public RoomStatus getStatus() {
		return status;
	}
	public void setStatus(RoomStatus status) {
		this.status = status;
	}
	public Set<PlayerRoomJT> getPlayerJT() {
		return playerJT;
	}
	public void setPlayerJT(Set<PlayerRoomJT> playerJT) {
		this.playerJT = playerJT;
	}
	public Set<SkillPlayerChangeJT> getRoomChangesToSkill() {
		return roomChangesToSkill;
	}
	public void setRoomChangesToSkill(Set<SkillPlayerChangeJT> roomChangesToSkill) {
		this.roomChangesToSkill = roomChangesToSkill;
	}
	public Set<SkillRoomJT> getSkills() {
		return skills;
	}
	public void setSkills(Set<SkillRoomJT> skills) {
		this.skills = skills;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((associatedDiscordRoom == null) ? 0 : associatedDiscordRoom.hashCode());
		result = prime * result + ((closed == null) ? 0 : closed.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + roomId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Room)) {
			return false;
		}
		Room other = (Room) obj;
		if (associatedDiscordRoom == null) {
			if (other.associatedDiscordRoom != null) {
				return false;
			}
		} else if (!associatedDiscordRoom.equals(other.associatedDiscordRoom)) {
			return false;
		}
		if (closed == null) {
			if (other.closed != null) {
				return false;
			}
		} else if (!closed.equals(other.closed)) {
			return false;
		}
		if (created == null) {
			if (other.created != null) {
				return false;
			}
		} else if (!created.equals(other.created)) {
			return false;
		}
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (roomId != other.roomId) {
			return false;
		}
		if (status != other.status) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", associatedDiscordRoom=" + associatedDiscordRoom + ", created=" + created
				+ ", closed=" + closed + ", description=" + description + ", status=" + status + "]";
	}
}