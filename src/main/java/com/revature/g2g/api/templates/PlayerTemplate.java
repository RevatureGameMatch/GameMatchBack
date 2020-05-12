package com.revature.g2g.api.templates;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;

import lombok.Data;

@Data
public class PlayerTemplate {
	private long playerId;
	private String playerUsername;
	private String playerEmail;
	private String playerPassword;
	private PlayerRole playerRole;
	private String message;
	public PlayerTemplate() {
		super();
		this.playerUsername = "Bob";
		this.playerEmail = "NotAnEmail@Gmail.com";
	}
	public PlayerTemplate(Player player) {
		this.setPlayerEmail(Jsoup.clean(player.getPlayerEmail(), Whitelist.none()));
		this.setPlayerId(player.getPlayerId());
		this.setPlayerPassword("****");
		this.setPlayerUsername(Jsoup.clean(player.getPlayerUsername(), Whitelist.none()));
		PlayerRole role = player.getPlayerRole();
		try {
			this.setPlayerRole(PlayerRole.valueOf(role.toString()));
		}catch (IllegalArgumentException e) {
			this.setPlayerRole(PlayerRole.PLAYER);
		}
	}
	public PlayerTemplate(PlayerTemplate template) {
		this.setPlayerEmail(Jsoup.clean(template.getPlayerEmail(), Whitelist.none()));
		this.setPlayerPassword(Jsoup.clean(template.getPlayerPassword(), Whitelist.none()));
		this.setPlayerUsername(Jsoup.clean(template.getPlayerUsername(), Whitelist.none()));
		this.setPlayerId(template.getPlayerId());
		PlayerRole role = template.getPlayerRole();
		try {
			this.setPlayerRole(PlayerRole.valueOf(role.toString()));
		}catch (IllegalArgumentException e) {
			this.setPlayerRole(PlayerRole.PLAYER);
		}
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