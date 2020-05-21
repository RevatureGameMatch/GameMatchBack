package com.revature.g2g.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.revature.g2g.services.helpers.SanitizerHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LoginDTO {
	@NotNull @NotBlank
	private String playerUsername;
	
	@NotNull @NotBlank
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
