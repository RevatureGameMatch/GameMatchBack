package com.revature.g2g.models;

import javax.validation.constraints.DecimalMin;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class SkillPlayerChangeDTO {
	private long skillPlayerChangeId;
	private SkillPlayerDTO skillPlayer;
	private PlayerDTO player;
	private PlayerDTO modifiedBy;
	
	@DecimalMin(value="0.01", inclusive=true)
	private float expertise;
	private RoomDTO room;
	
	@DecimalMin(value="0.01", inclusive=true)
	private float value;

	public SkillPlayerChangeDTO(SkillPlayerChangeJT source) {
		super();
		this.skillPlayerChangeId = source.getSkillPlayerChangeJTId();
		this.skillPlayer = new SkillPlayerDTO(source.getSkillPlayerJT() );
		this.player = new PlayerDTO(source.getPlayer() );
		this.modifiedBy = new PlayerDTO(source.getModifiedBy() );
		this.expertise = source.getExpertise();
		this.room = new RoomDTO(source.getRoom() );
		this.value = source.getValue();
	}
}
