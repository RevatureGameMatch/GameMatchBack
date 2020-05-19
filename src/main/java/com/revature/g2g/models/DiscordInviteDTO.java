package com.revature.g2g.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class DiscordInviteDTO {
	private long id;
	private long guildId;
	private String guildName;
	private long channelId;
	private String channelName;
	private long inviteId;
	private String inviteCode;
	private String urlApp;
	private String urlWeb;
	private String username;
	// TODO: Isn't this something we need to protect?  Different class may be needed.
	private String discordCode;
	private RoomDTO room;
	private PlayerDTO player;
	private DiscordInviteStatus status;
	
	public DiscordInviteDTO(DiscordInvite invite) {
		super();
		this.setId(invite.getId());
		this.setUsername(Jsoup.clean(invite.getUsername(), Whitelist.none()) );
		this.setDiscordCode(invite.getDiscordCode() );
		this.setRoom(new RoomDTO(invite.getRoom()) );
		this.setPlayer(new PlayerDTO(invite.getPlayer()) );
		this.setStatus(invite.getStatus() );
	}
	
	public DiscordInviteDTO(DiscordInviteDTO source) {
		super();
		this.setId(source.getId());
		this.setGuildId(source.getGuildId());
		this.setGuildName(Jsoup.clean(source.getGuildName(), Whitelist.none()) );
		this.setChannelId(source.getChannelId() );
		this.setChannelName(Jsoup.clean(source.getChannelName(), Whitelist.none()) );
		this.setInviteId(source.getInviteId());
		this.setInviteCode(source.getInviteCode());
		this.setUrlApp(Jsoup.clean(source.getUrlApp(), Whitelist.none()) );
		this.setUrlWeb(Jsoup.clean(source.getUrlWeb(), Whitelist.none()) );
		this.setUsername(Jsoup.clean(source.getUsername(), Whitelist.none()) );
		this.setDiscordCode(source.getDiscordCode() );
		this.setRoom(source.getRoom() );
		this.setPlayer(source.getPlayer() );
		this.setStatus(source.getStatus() );
		
	}

}
