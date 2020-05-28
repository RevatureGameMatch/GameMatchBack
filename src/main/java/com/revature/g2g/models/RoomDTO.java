package com.revature.g2g.models;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;

import com.revature.g2g.services.helpers.SanitizerHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class RoomDTO {
	private long roomId;
	
	@Digits(integer=18, fraction = 0)
	private long discordTextChannelId;
	
	@Digits(integer=18, fraction = 0)
	private long discordVoiceChannelId;
	
	@Digits(integer=6, fraction = 0)
	private long discordRoleId;
	
	@PastOrPresent
	private Date created;
	
	@PastOrPresent
	private Date closed;
	
	private String name;
	
	@Min(value=1)
	private int currentPlayers;
	
	@Min(value=2)
	private int maxPlayers;
	
	private String description;
	private RoomStatus status;
	private RoomPlayStyle style;
	private GameDTO game;
	private RoomDTO room;
	private PlayerDTO sender;

	public RoomDTO(Room source) {
		super();
		this.roomId = source.getRoomId();
		this.discordTextChannelId = source.getDiscordTextChannelId();
		this.discordVoiceChannelId = source.getDiscordVoiceChannelId();
		this.discordRoleId = source.getDiscordRoleId();
		this.created = source.getCreated();
		this.closed = source.getClosed();
		this.setName(SanitizerHelper.sanitize(source.getName()) );
		this.currentPlayers = source.getCurrentPlayers();
		this.maxPlayers =  source.getMaxPlayers();
		this.setDescription(SanitizerHelper.sanitize(source.getDescription()) );
		this.status = source.getStatus();
		this.style = source.getStyle();
		this.game = new GameDTO(source.getGame() );
	}

	public RoomDTO(RoomDTO source) {
		super();
		this.roomId = source.getRoomId();
		this.discordTextChannelId = source.getDiscordTextChannelId();
		this.discordVoiceChannelId = source.getDiscordVoiceChannelId();
		this.discordRoleId = source.getDiscordRoleId();
		this.created = source.getCreated();
		this.closed = source.getClosed();
		this.setName(SanitizerHelper.sanitize(source.getName()) );
		this.currentPlayers = source.getCurrentPlayers();
		this.maxPlayers =  source.getMaxPlayers();
		this.setDescription(SanitizerHelper.sanitize(source.getDescription()) );
		this.status = source.getStatus();
		this.style = source.getStyle();
		this.game = new GameDTO(source.getGame() );
		this.room = new RoomDTO(source.getRoom() );
		this.sender = new PlayerDTO(source.getSender() );
	}

}
