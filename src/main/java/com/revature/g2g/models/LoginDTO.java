package com.revature.g2g.models;

import com.revature.g2g.services.helpers.SanitizerHelper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LoginDTO {
	private String username;
	private String password;
	
	public LoginDTO(String neoUserName, String neoPassword) {
		super();
		this.setUsername(neoUserName);
		this.setPassword(neoPassword);
	}
	
	public LoginDTO(LoginDTO other) {
		this(other.getUsername(), other.getPassword() );
	}
	
	public void setPassword(String source) {
		this.password = SanitizerHelper.sanitize(source);
	}

	public void setUsername(String source) {
		this.username = SanitizerHelper.sanitize(source);
	}

}
