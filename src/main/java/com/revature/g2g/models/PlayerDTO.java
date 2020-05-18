package com.revature.g2g.models;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PlayerDTO {
	private long playerId;
	private String playerUsername;
	private String playerEmail;
	private PlayerRole  playerRole;
	private Set<PlayerRoomJT> roomJT = new HashSet<>();
	private Set<SkillPlayerChangeJT> changesToSelf = new HashSet<>();
	private Set<SkillPlayerChangeJT> changesToOthers = new HashSet<>();
	private Set<SkillPlayerJT> skills = new HashSet<>();

	public PlayerDTO(Player player) {
		super();
		this.playerId = player.getPlayerId();
		this.playerUsername = player.getPlayerUsername();
		this.playerEmail = player.getPlayerEmail();
		this.playerRole = player.getPlayerRole();
		this.roomJT = player.getRoomJT();
		this.changesToSelf = player.getChangesToSelf();
		this.changesToOthers = player.getChangesToOthers();
		this.skills = player.getSkills();
	}

}
