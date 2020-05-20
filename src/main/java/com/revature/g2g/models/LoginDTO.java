package com.revature.g2g.models;

import com.revature.g2g.data.DataInput;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LoginDTO {
	private String username;
	private String password;
	
	public LoginDTO(String neoUserName, String neoPassword) {
		super();
		this.setUsername(DataInput.sanitize(neoUserName) );
		this.setPassword(DataInput.sanitize(neoPassword) );
	}
	
	public LoginDTO(LoginDTO other) {
		this(other.getUsername(), other.getPassword() );
	}

}
