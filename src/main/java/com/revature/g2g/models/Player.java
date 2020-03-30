package com.revature.g2g.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "G2G_PLAYER")
public class Player implements Serializable {
	private static final long serialVersionUID = 5673274454063809268L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column(name = "player_id")
	private int playerId;
	
	@Column(name = "player_username", unique = true)
	private String playerUsername;
	
	@Column(name = "player_email", unique = true)
	private String playerEmail;
	
	@Column(name = "player_password")
	private String playerPassword;
	
	@Column(name = "player_role")
	private PlayerRole  playerRole;

	public Player() {
		super();
	}
	public Player(int playerId, String playerUsername, String playerEmail, String playerPassword,
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerEmail == null) ? 0 : playerEmail.hashCode());
		result = prime * result + playerId;
		result = prime * result + ((playerPassword == null) ? 0 : playerPassword.hashCode());
		result = prime * result + ((playerRole == null) ? 0 : playerRole.hashCode());
		result = prime * result + ((playerUsername == null) ? 0 : playerUsername.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		if (playerEmail == null) {
			if (other.playerEmail != null) {
				return false;
			}
		} else if (!playerEmail.equals(other.playerEmail)) {
			return false;
		}
		if (playerId != other.playerId) {
			return false;
		}
		if (playerPassword == null) {
			if (other.playerPassword != null) {
				return false;
			}
		} else if (!playerPassword.equals(other.playerPassword)) {
			return false;
		}
		if (playerRole != other.playerRole) {
			return false;
		}
		if (playerUsername == null) {
			if (other.playerUsername != null) {
				return false;
			}
		} else if (!playerUsername.equals(other.playerUsername)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerUsername=" + playerUsername + ", playerEmail=" + playerEmail
				+ ", playerPassword=" + playerPassword + ", playerRole=" + playerRole + "]";
	}
}