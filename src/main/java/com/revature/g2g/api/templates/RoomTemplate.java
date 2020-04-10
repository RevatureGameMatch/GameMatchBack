package com.revature.g2g.api.templates;

import java.util.Objects;

import com.revature.g2g.models.Room;

public class RoomTemplate {
	private Room room;
	private PlayerTemplate sender;
	public RoomTemplate() {
		super();
	}
	public RoomTemplate(Room room, PlayerTemplate sender) {
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
		return Objects.hash(sender, room);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RoomTemplate)) {
			return false;
		}
		RoomTemplate other = (RoomTemplate) obj;
		return Objects.equals(sender, other.sender) && Objects.equals(room, other.room);
	}
	@Override
	public String toString() {
		return "RoomTemplate [room=" + room + ", sender=" + sender + "]";
	}
}