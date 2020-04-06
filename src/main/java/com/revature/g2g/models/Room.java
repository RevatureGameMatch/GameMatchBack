package com.revature.g2g.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component()
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Entity
@Table(name = "G2G_ROOM")
public class Room implements Serializable{
	private static final long serialVersionUID = -5444471863298926133L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private int roomId;
	
	@Column(name = "discord_text_channel_id")
	private Long discordTextChannelId;
	
	@Column(name = "discord_voice_channel_id")
	private Long discordVoiceChannelId;
	
	@Column(name = "discord_role_id")
	private Long discordRoleId;
	
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
	public Room(Long discordTextChannelId, Long discordVoiceChannelId, Long discordRoleId, Date created,
			Date closed, String description, RoomStatus status) {
		super();
		this.discordTextChannelId = discordTextChannelId;
		this.discordVoiceChannelId = discordVoiceChannelId;
		this.discordRoleId = discordRoleId;
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
	public Long getDiscordTextChannelId() {
		return discordTextChannelId;
	}
	public void setDiscordTextChannelId(Long discordTextChannelId) {
		this.discordTextChannelId = discordTextChannelId;
	}
	public Long getDiscordVoiceChannelId() {
		return discordVoiceChannelId;
	}
	public void setDiscordVoiceChannelId(Long discordVoiceChannelId) {
		this.discordVoiceChannelId = discordVoiceChannelId;
	}
	public Long getDiscordRoleId() {
		return discordRoleId;
	}
	public void setDiscordRoleId(Long discordRoleId) {
		this.discordRoleId = discordRoleId;
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
		return Objects.hash(closed, created, description, discordRoleId, discordTextChannelId, discordVoiceChannelId,
				roomId, status);
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
		return Objects.equals(closed, other.closed) && Objects.equals(created, other.created)
				&& Objects.equals(description, other.description) && Objects.equals(discordRoleId, other.discordRoleId)
				&& Objects.equals(discordTextChannelId, other.discordTextChannelId)
				&& Objects.equals(discordVoiceChannelId, other.discordVoiceChannelId) && roomId == other.roomId
				&& status == other.status;
	}
	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", discordTextChannelId=" + discordTextChannelId + ", discordVoiceChannelId="
				+ discordVoiceChannelId + ", discordRoleId=" + discordRoleId + ", created=" + created + ", closed="
				+ closed + ", description=" + description + ", status=" + status + "]";
	}
}