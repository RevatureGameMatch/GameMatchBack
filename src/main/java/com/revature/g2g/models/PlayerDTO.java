package com.revature.g2g.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

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
		this.setPlayerEmail(DataInput.sanitize(source.getPlayerEmail()) );
		this.setPlayerUsername(DataInput.sanitize(source.getPlayerUsername()) );
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
		this.setPlayerEmail(DataInput.sanitize(source.getPlayerEmail()) );
		this.setPlayerUsername(DataInput.sanitize(source.getPlayerUsername()) );
		this.setPlayerId(source.getPlayerId());
		PlayerRole role = source.getPlayerRole();
		try {
			this.setPlayerRole(PlayerRole.valueOf(role.toString()));
		}catch (IllegalArgumentException e) {
			this.setPlayerRole(PlayerRole.PLAYER);
		}
		this.setMessage(DataInput.sanitize(source.getMessage()) );
	}

}
