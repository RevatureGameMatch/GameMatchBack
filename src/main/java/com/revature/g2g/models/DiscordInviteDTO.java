package com.revature.g2g.models;

import com.revature.g2g.services.helpers.SanitizerHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class DiscordInviteDTO {
	private long id;
	private String urlApp;
	private String urlWeb;
	private String username;
	private String discordCode;
	private RoomDTO room;
	private PlayerDTO player;
	private DiscordInviteStatus status;
	
	public DiscordInviteDTO(DiscordInvite invite) {
		super();
		this.setId(invite.getId());
		this.setUsername(invite.getUsername() );
		this.setDiscordCode(invite.getDiscordCode() );
		this.setRoom(new RoomDTO(invite.getRoom()) );
		this.setPlayer(new PlayerDTO(invite.getPlayer()) );
		this.setStatus(invite.getStatus() );
	}
	
	public DiscordInviteDTO(DiscordInviteDTO source) {
		super();
		this.setId(source.getId());
		this.setUrlApp(source.getUrlApp() );
		this.setUrlWeb(source.getUrlWeb() );
		this.setUsername(source.getUsername() );
		this.setDiscordCode(source.getDiscordCode() );
		this.setRoom(source.getRoom() );
		this.setPlayer(source.getPlayer() );
		this.setStatus(source.getStatus() );
		
	}

	public void setUrlApp(String source) {
		this.urlApp = SanitizerHelper.sanitize(source);
	}
	
	public void setUrlWeb(String source) {
		this.urlWeb = SanitizerHelper.sanitize(source);
	}
	
	public void setUsername(String source) {
		this.username = SanitizerHelper.sanitize(source);
	}
	
	public void setDiscordCode(String source) {
		this.discordCode = SanitizerHelper.sanitize(source);
		this.urlApp = String.format("discord://discordapp.com/invite/%s", this.discordCode);
		this.urlWeb = String.format("https://discord.gg/%s", this.discordCode);
	}
}
