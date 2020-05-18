package com.revature.g2g.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SkillRoomDTO {
	private long skillRoomId;
	private SkillDTO skill;
	private RoomDTO room;
	private double minValue;
	
	public SkillRoomDTO(SkillRoomJT source) {
		super();
		this.skillRoomId = source.getSkillRoomJTId();
		this.skill = new SkillDTO(source.getSkill() );
		this.room = new RoomDTO(source.getRoom() );
		this.minValue = source.getMinValue();
	}

}
