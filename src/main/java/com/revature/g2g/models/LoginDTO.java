package com.revature.g2g.models;

import com.revature.g2g.data.DataInput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LoginDTO {
	private String playerUsername;
	private String playerPassword;
	
	public LoginDTO(String neoUserName, String neoPassword) {
		super();
		this.setUsername(neoUserName);
		this.setPassword(neoPassword);
	}
	
	public LoginDTO(LoginDTO other) {
		this(other.getPlayerUsername(), other.getPlayerPassword() );
	}
	
	public void setPassword(String source) {
		this.playerPassword = DataInput.sanitize(source);
	}

	public void setUsername(String source) {
		this.playerUsername = DataInput.sanitize(source);
	}

}
