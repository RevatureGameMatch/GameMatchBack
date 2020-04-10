package com.revature.g2g.models;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "G2G_PLAYER_ROOM_JT")
public class PlayerRoomJT implements Serializable{
	private static final long serialVersionUID = -7131529712503037883L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "player_room_jt_id")
	private int playerRoomJTId;
	
	@NotNull(message="PlayerRoomJT requires a player.")
	@ManyToOne()
	@JoinColumn(name = "player_id")
	private Player player;
	
	@NotNull(message="PlayerRoomJT requires a room.")
	@ManyToOne()
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "joined")
	private Date joined;
	
	@Column(name = "left_room")
	private Date left;

	public PlayerRoomJT() {
		super();
	}
	public PlayerRoomJT(int playerRoomJTId, Player player, Room room, Date joined, Date left) {
		super();
		this.playerRoomJTId = playerRoomJTId;
		this.player = player;
		this.room = room;
		this.joined = joined;
		this.left = left;
	}
	public int getPlayerRoomJTId() {
		return playerRoomJTId;
	}
	public void setPlayerRoomJTId(int playerRoomJTId) {
		this.playerRoomJTId = playerRoomJTId;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Date getJoined() {
		return joined;
	}
	public void setJoined(Date joined) {
		this.joined = joined;
	}
	public Date getLeft() {
		return left;
	}
	public void setLeft(Date left) {
		this.left = left;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((player == null) ? 0 : player.hashCode());
		result = prime * result + playerRoomJTId;
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PlayerRoomJT)) {
			return false;
		}
		PlayerRoomJT other = (PlayerRoomJT) obj;
		if (player == null) {
			if (other.player != null) {
				return false;
			}
		} else if (!player.equals(other.player)) {
			return false;
		}
		if (playerRoomJTId != other.playerRoomJTId) {
			return false;
		}
		if (room == null) {
			if (other.room != null) {
				return false;
			}
		} else if (!room.equals(other.room)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "PlayerRoomJT [playerRoomJTId=" + playerRoomJTId + ", player=" + player + ", room=" + room + "]";
	}
}