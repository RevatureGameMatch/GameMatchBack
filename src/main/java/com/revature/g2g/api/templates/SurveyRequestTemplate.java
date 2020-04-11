package com.revature.g2g.api.templates;

import java.util.Objects;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;

public class SurveyRequestTemplate {
	private Player player;
	private Room room;
	public SurveyRequestTemplate() {
		super();
	}
	public SurveyRequestTemplate(Player player, Room room) {
		super();
		this.player = player;
		this.room = room;
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
	@Override
	public int hashCode() {
		return Objects.hash(player, room);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof SurveyRequestTemplate)) {
			return false;
		}
		SurveyRequestTemplate other = (SurveyRequestTemplate) obj;
		return Objects.equals(player, other.player) && Objects.equals(room, other.room);
	}
	@Override
	public String toString() {
		return "SurveyRequestTemplate [player=" + player + ", room=" + room + "]";
	}
}