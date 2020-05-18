package com.revature.g2g.models;

import java.util.Date;

public class RoomDTO {
	private long roomId;
	private long discordTextChannelId;
	private long discordVoiceChannelId;
	private long discordRoleId;
	private Date created;
	private Date closed;
	private String name;
	private int currentPlayers;
	private int maxPlayers;
	private String description;
	private RoomStatus status;
	private RoomPlayStyle style;
	private GameDTO game;

	public RoomDTO(Room room) {
		super();
		this.roomId = room.getRoomId();
		this.discordTextChannelId = room.getDiscordTextChannelId();
		this.discordVoiceChannelId = room.getDiscordVoiceChannelId();
		this.discordRoleId = room.getDiscordRoleId();
		this.created = room.getCreated();
		this.closed = room.getClosed();
		this.name = room.getName();
		this.currentPlayers = room.getCurrentPlayers();
		this.maxPlayers =  room.getMaxPlayers();
		this.description =  room.getDescription();
		this.status = room.getStatus();
		this.style = room.getStyle();
		this.game = new GameDTO(room.getGame() );
	}

}
