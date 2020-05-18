package com.revature.g2g.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PlayerDTO {
	private long playerId;
	private String playerUsername;
	private String playerEmail;
	private PlayerRole  playerRole;

	public PlayerDTO(Player player) {
		super();
		this.playerId = player.getPlayerId();
		this.playerUsername = player.getPlayerUsername();
		this.playerEmail = player.getPlayerEmail();
		this.playerRole = player.getPlayerRole();
	}

}
