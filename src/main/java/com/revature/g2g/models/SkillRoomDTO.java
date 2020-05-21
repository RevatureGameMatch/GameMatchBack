package com.revature.g2g.models;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Positive;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SkillRoomDTO {
	@Positive
	private long skillRoomId;
	
	private SkillDTO skill;
	private RoomDTO room;
	
	@DecimalMin(value="0.01", inclusive=true)
	private double minValue;
	
	public SkillRoomDTO(SkillRoomJT source) {
		super();
		this.skillRoomId = source.getSkillRoomJTId();
		this.skill = new SkillDTO(source.getSkill() );
		this.room = new RoomDTO(source.getRoom() );
		this.minValue = source.getMinValue();
	}

}
