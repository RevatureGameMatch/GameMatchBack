package com.revature.g2g.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class LoginDTO {
	private String username;
	private String password;
	
	public LoginDTO(String neoUserName, String neoPassword) {
		super();
		this.setUsername(Jsoup.clean(neoUserName, Whitelist.none()));
		this.setPassword(Jsoup.clean(neoPassword, Whitelist.none()));
	}
	
	public LoginDTO(LoginDTO other) {
		this(other.getUsername(), other.getPassword() );
	}

}
