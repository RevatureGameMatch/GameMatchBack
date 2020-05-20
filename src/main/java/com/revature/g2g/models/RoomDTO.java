package com.revature.g2g.models;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.revature.g2g.services.helpers.SanitizerHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
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
