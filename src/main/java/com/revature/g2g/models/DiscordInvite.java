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
@Table(name = "G2G_DISCORD_INVITE")
public class DiscordInvite implements Serializable{
	private static final long serialVersionUID = 7444308037511438669L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invite_id")
	private long id;
	
	@Column(name = "invite_username")
	private String username;
	
	@Column(name = "invite_discord_code")
	private String discordCode;
	
	@NotNull(message = "The DiscordInvite must havea a room.")
	@ManyToOne()
	@JoinColumn(name = "invite_room")
	private Room room;
	
	@NotNull(message = "The DiscordInvite must have a player.")
	@ManyToOne()
	@JoinColumn(name = "invite_player")
	private Player player;
	
	@Column(name="invite_status")
	private DiscordInviteStatus status;

	public DiscordInvite() {
		super();
		this.room = new Room();
		this.player = new Player();
	}
	public DiscordInvite(long id, String username, String discordCode, Room room, Player player,
			DiscordInviteStatus status) {
		super();
		this.id = id;
		this.username = username;
		this.discordCode = discordCode;
		this.room = room;
		this.player = player;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDiscordCode() {
		return discordCode;
	}
	public void setDiscordCode(String discordCode) {
		this.discordCode = discordCode;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public DiscordInviteStatus getStatus() {
		return status;
	}
	public void setStatus(DiscordInviteStatus status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		return Objects.hash(discordCode, id, player, room, status, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DiscordInvite)) {
			return false;
		}
		DiscordInvite other = (DiscordInvite) obj;
		return discordCode == other.discordCode && id == other.id && Objects.equals(player, other.player)
				&& Objects.equals(room, other.room) && status == other.status
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "DiscordInvite [id=" + id + ", username=" + username + ", discordCode=" + discordCode + ", room=" + room
				+ ", player=" + player + ", status=" + status + "]";
	}
}