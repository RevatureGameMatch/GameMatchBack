package com.revature.g2g.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "roomJT","changesToSelf", "changesToOthers", "skills" })
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
	
	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<PlayerRoomJT> roomJT = new HashSet<>();

	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> changesToSelf = new HashSet<>();

	@OneToMany(mappedBy = "modifiedBy", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> changesToOthers = new HashSet<>();

	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<SkillPlayerJT> skills = new HashSet<>();

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
	public Set<PlayerRoomJT> getRoomJT() {
		return roomJT;
	}
	public void setRoomJT(Set<PlayerRoomJT> roomJT) {
		this.roomJT = roomJT;
	}
	public Set<SkillPlayerChangeJT> getChangesToSelf() {
		return changesToSelf;
	}
	public void setChangesToSelf(Set<SkillPlayerChangeJT> changesToSelf) {
		this.changesToSelf = changesToSelf;
	}
	public Set<SkillPlayerChangeJT> getChangesToOthers() {
		return changesToOthers;
	}
	public void setChangesToOthers(Set<SkillPlayerChangeJT> changesToOthers) {
		this.changesToOthers = changesToOthers;
	}
	public Set<SkillPlayerJT> getSkills() {
		return skills;
	}
	public void setSkills(Set<SkillPlayerJT> skills) {
		this.skills = skills;
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
		if (!(obj instanceof Player)) {
			return false;
		}
		Player other = (Player) obj;
		return Objects.equals(playerEmail, other.playerEmail) && playerId == other.playerId
				&& Objects.equals(playerPassword, other.playerPassword) && playerRole == other.playerRole
				&& Objects.equals(playerUsername, other.playerUsername);
	}
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerUsername=" + playerUsername + ", playerEmail=" + playerEmail
				+ ", playerPassword=" + playerPassword + ", playerRole=" + playerRole + "]";
	}
}