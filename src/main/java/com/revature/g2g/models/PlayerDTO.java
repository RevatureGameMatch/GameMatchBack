package com.revature.g2g.models;

import com.revature.g2g.api.templates.PlayerTemplate;

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

	/**
	 * This method is depreciated, and can be removed once PlayerTemplate has been removed.
	 * 
	 * @param source
	 */
	public PlayerDTO(PlayerTemplate source) {
		super();
		this.playerId = source.getPlayerId();
		this.playerUsername = source.getPlayerUsername();
		this.playerEmail = source.getPlayerEmail();
		this.playerRole = source.getPlayerRole();
	}

}
