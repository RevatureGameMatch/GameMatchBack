package com.revature.g2g.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.revature.g2g.data.DataInput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class DiscordInviteDTO {
	private long id;
	private long guildId;
	private String guildName;
	private long channelId;
	private String channelName;
	private String inviteCode;
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
		this.setGuildId(source.getGuildId());
		this.setGuildName(source.getGuildName() );
		this.setChannelId(source.getChannelId() );
		this.setChannelName(source.getChannelName() );
		this.setInviteCode(source.getInviteCode());
		this.setUrlApp(source.getUrlApp() );
		this.setUrlWeb(source.getUrlWeb() );
		this.setUsername(source.getUsername() );
		this.setDiscordCode(source.getDiscordCode() );
		this.setRoom(source.getRoom() );
		this.setPlayer(source.getPlayer() );
		this.setStatus(source.getStatus() );
		
	}

	public void setGuildName(String source) {
		this.guildName = DataInput.sanitize(source);
	}
	
	public void setChannelName(String source) {
		this.channelName = DataInput.sanitize(source);
	}
		
	public void setUrlApp(String source) {
		this.urlApp = DataInput.sanitize(source);
	}
	
	public void setUrlWeb(String source) {
		this.urlWeb = DataInput.sanitize(source);
	}
	
	public void setUsername(String source) {
		this.username = DataInput.sanitize(source);
	}
	
	public void setDiscordCode(String source) {
		this.discordCode = DataInput.sanitize(source);
	}
}
