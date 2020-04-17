package com.revature.g2g.api.templates;

import java.util.Objects;

import org.hibernate.validator.constraints.NotBlank;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;

public class PlayerTemplate {
	private int playerId;
	@NotBlank
	private String playerUsername;
	@NotBlank
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
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayerUsername() {
		return playerUsername;
	}
	public void setPlayerUsername(String playerUsername) {
		this.playerUsername = playerUsername;
	}
	public String getPlayerEmail() {
		return playerEmail;
	}
	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}
	public String getPlayerPassword() {
		return playerPassword;
	}
	public void setPlayerPassword(String playerPassword) {
		this.playerPassword = playerPassword;
	}
	public PlayerRole getPlayerRole() {
		return playerRole;
	}
	public void setPlayerRole(PlayerRole playerRole) {
		this.playerRole = playerRole;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public int hashCode() {
		return Objects.hash(playerEmail, playerId, playerPassword, playerRole, playerUsername);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof PlayerTemplate)) {
			return false;
		}
		PlayerTemplate other = (PlayerTemplate) obj;
		return Objects.equals(playerEmail, other.playerEmail) && playerId == other.playerId
				&& Objects.equals(playerPassword, other.playerPassword) && playerRole == other.playerRole
				&& Objects.equals(playerUsername, other.playerUsername);
	}
	@Override
	public String toString() {
		return "PlayerTemplate [playerId=" + playerId + ", playerUsername=" + playerUsername + ", playerEmail="
				+ playerEmail + ", playerPassword=" + playerPassword + ", playerRole=" + playerRole + "]";
	}
}