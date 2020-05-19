package com.revature.g2g.api.templates;

import java.util.Objects;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.SurveyDTO;

public class SurveyRoomTemplate {
	@NotNull
	private Room room;
	private List<SurveyDTO> surveyList;
	public SurveyRoomTemplate() {
		super();
		this.room = new Room();
	}
	public SurveyRoomTemplate(Room room, List<SurveyDTO> surveyList) {
		super();
		this.room = room;
		this.surveyList = surveyList;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<SurveyDTO> getSurveyList() {
		return surveyList;
	}
	public void setSurveyList(List<SurveyDTO> surveyList) {
		this.surveyList = surveyList;
	}
	@Override
	public int hashCode() {
		return Objects.hash(room, surveyList);
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
		return Objects.equals(room, other.room) && Objects.equals(surveyList, other.surveyList);
	}
	@Override
	public String toString() {
		return "SurveyRoomTemplate [room=" + room + ", surveyList=" + surveyList + "]";
	}
}
