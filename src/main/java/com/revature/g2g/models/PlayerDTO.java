package com.revature.g2g.models;

import org.hibernate.validator.constraints.Email;

import com.revature.g2g.services.helpers.SanitizerHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PlayerDTO {
	private long playerId;
	
	private String playerUsername;
	
	@Email
	private String playerEmail;
	
	private PlayerRole  playerRole;
	private String message;

	public PlayerDTO(Player source) {
		super();
		this.setPlayerEmail(source.getPlayerEmail() );
		this.setPlayerUsername(source.getPlayerUsername() );
		this.setPlayerId(source.getPlayerId());
		PlayerRole role = source.getPlayerRole();
		try {
			this.setPlayerRole(PlayerRole.valueOf(role.toString()));
		}catch (IllegalArgumentException e) {
			this.setPlayerRole(PlayerRole.PLAYER);
		}
	}

	public PlayerDTO(PlayerDTO source) {
		super();
		this.setPlayerEmail(source.getPlayerEmail() );
		this.setPlayerUsername(source.getPlayerUsername() );
		this.setPlayerId(source.getPlayerId());
		PlayerRole role = source.getPlayerRole();
		try {
			this.setPlayerRole(PlayerRole.valueOf(role.toString()));
		}catch (IllegalArgumentException e) {
			this.setPlayerRole(PlayerRole.PLAYER);
		}
		this.setMessage(SanitizerHelper.sanitize(source.getMessage()) );
	}

	public void setPlayerUsername(String source) {
		this.playerUsername = SanitizerHelper.sanitize(source);
	}
	
	public void setPlayerEmail(String source) {
		this.playerEmail = SanitizerHelper.sanitize(source);
	}
}
