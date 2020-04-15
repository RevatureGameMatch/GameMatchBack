package com.revature.g2g.api.templates;

import java.util.Objects;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.revature.g2g.models.Room;

public class SurveyRoomTemplate {
	@NotNull
	private Room room;
	private Set<SurveyTemplate> surveySet;
	public SurveyRoomTemplate() {
		super();
	}
	public SurveyRoomTemplate(Room room, Set<SurveyTemplate> surveySet) {
		super();
		this.room = room;
		this.surveySet = surveySet;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public Set<SurveyTemplate> getSurveySet() {
		return surveySet;
	}
	public void setSurveySet(Set<SurveyTemplate> surveySet) {
		this.surveySet = surveySet;
	}
	@Override
	public int hashCode() {
		return Objects.hash(room, surveySet);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SurveyRoomTemplate other = (SurveyRoomTemplate) obj;
		return Objects.equals(room, other.room) && Objects.equals(surveySet, other.surveySet);
	}
	@Override
	public String toString() {
		return "SurveyRoomTemplate [room=" + room + ", surveySet=" + surveySet + "]";
	}
}
