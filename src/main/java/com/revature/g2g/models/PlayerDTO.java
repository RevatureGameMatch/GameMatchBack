package com.revature.g2g.models;

import com.revature.g2g.data.DataInput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PlayerDTO {
	private long playerId;
	private String playerUsername;
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
		this.setMessage(DataInput.sanitize(source.getMessage()) );
	}

	public void setPlayerUsername(String source) {
		this.playerUsername = DataInput.sanitize(source);
	}
	
	public void setPlayerEmail(String source) {
		this.playerEmail = DataInput.sanitize(source);
	}
}
