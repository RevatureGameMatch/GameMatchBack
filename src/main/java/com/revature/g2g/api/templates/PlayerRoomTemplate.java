package com.revature.g2g.api.templates;

import java.util.Objects;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;

public class PlayerRoomTemplate {
	private Room room;
	private PlayerTemplate sender;
	public PlayerRoomTemplate() {
		super();
	}
	public PlayerRoomTemplate(Room room, PlayerTemplate sender) {
		super();
		this.room = room;
		this.sender = sender;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public PlayerTemplate getSender() {
		return sender;
	}
	public void setSender(PlayerTemplate sender) {
		this.sender = sender;
	}
	@Override
	public int hashCode() {
		return Objects.hash(room, sender);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PlayerRoomTemplate)) {
			return false;
		}
		PlayerRoomTemplate other = (PlayerRoomTemplate) obj;
		return Objects.equals(room, other.room) && Objects.equals(sender, other.sender);
	}
	@Override
	public String toString() {
		return "PlayerRoomTemplate [room=" + room + ", sender=" + sender + "]";
	}
}