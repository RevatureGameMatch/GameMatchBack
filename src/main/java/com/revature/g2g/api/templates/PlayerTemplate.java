package com.revature.g2g.api.templates;

import com.revature.g2g.models.PlayerRole;

public class PlayerTemplate {
	private int playerId;
	private String playerUsername;
	private String playerEmail;
	private String playerPassword;
	private PlayerRole playerRole;
	public PlayerTemplate() {
		super();
	}
	public PlayerTemplate(int playerId, String playerUsername, String playerEmail, String playerPassword,
			PlayerRole playerRole) {
		super();
		this.playerId = playerId;
		this.playerUsername = playerUsername;
		this.playerEmail = playerEmail;
		this.playerPassword = playerPassword;
		this.playerRole = playerRole;
	}
}