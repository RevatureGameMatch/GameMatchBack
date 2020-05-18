package com.revature.g2g.models;

public class SkillPlayerChangeDTO {
	private long skillPlayerChangeId;
	private SkillPlayerDTO skillPlayer;
	private PlayerDTO player;
	private PlayerDTO modifiedBy;
	private float expertise;
	private RoomDTO room;
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
