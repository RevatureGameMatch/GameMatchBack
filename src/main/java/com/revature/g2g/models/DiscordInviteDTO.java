package com.revature.g2g.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class DiscordInviteDTO {
	private long id;
	private String username;
	private String discordCode;
	private RoomDTO room;
	private PlayerDTO player;
	private DiscordInviteStatus status;
	
	public DiscordInviteDTO(DiscordInvite invite) {
		super();
		this.id = invite.getId();
		this.username = invite.getUsername();
		this.discordCode = invite.getDiscordCode();
		this.room = new RoomDTO(invite.getRoom() );
		this.player = new PlayerDTO(invite.getPlayer() );
		this.status = invite.getStatus();
	}

}
