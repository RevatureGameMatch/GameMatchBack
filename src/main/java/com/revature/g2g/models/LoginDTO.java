package com.revature.g2g.models;

import com.revature.g2g.services.helpers.SanitizerHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LoginDTO {
	private String playerUsername;
	private String playerPassword;
	
	public LoginDTO(String neoUserName, String neoPassword) {
		super();
		this.setPlayerUsername(neoUserName);
		this.setPlayerPassword(neoPassword);
	}
	
	public LoginDTO(LoginDTO other) {
		this(other.getPlayerUsername(), other.getPlayerPassword() );
	}
	
	public void setPlayerPassword(String source) {

		this.playerPassword = SanitizerHelper.sanitize(source);
	}

	public void setPlayerUsername(String source) {
		this.playerUsername = SanitizerHelper.sanitize(source);
	}

}
