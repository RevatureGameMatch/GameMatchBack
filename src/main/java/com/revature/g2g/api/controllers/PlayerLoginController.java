package com.revature.g2g.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.exceptions.PasswordMatchFailed;
import com.revature.g2g.exceptions.UserNotFound;
import com.revature.g2g.models.LoginDTO;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerDTO;
import com.revature.g2g.services.business.LoginService;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PasswordHelper;

@CrossOrigin
@RestController
@RequestMapping(value="/player-login")
public class PlayerLoginController {
	PlayerHandler playerHandler;
	PasswordHelper passwordHelper;
	LoggerSingleton loggerSingleton;
	LoginService loginService;
	
	@Autowired
	public PlayerLoginController(PlayerHandler playerHandler, PasswordHelper passwordHelper,
			LoggerSingleton loggerSingleton, LoginService loginService) {
		super();
		this.playerHandler = playerHandler;
		this.passwordHelper = passwordHelper;
		this.loggerSingleton = loggerSingleton;
		this.loginService = loginService;
	}
	
	@PostMapping
	public ResponseEntity<PlayerDTO> login(@RequestBody LoginDTO dto){
		if(dto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		try {
			Player player = loginService.login(dto.getPlayerUsername(), dto.getPlayerPassword());
			if(player != null) {
				String loginMessage = "PlayerLoginController: player logged in: " + player.getPlayerUsername();
				loggerSingleton.getAccessLog().trace(loginMessage);
				return ResponseEntity.status(HttpStatus.ACCEPTED).body(new PlayerDTO(player) );
			}
		}catch(PasswordMatchFailed e) {
			String invalidPasswordMessage = "INVALID PASSWORD login fail: " + dto.getPlayerUsername();
			loggerSingleton.getAccessLog().warn(invalidPasswordMessage);
		}catch(UserNotFound e) {
			String invalidUsername = "USER NOT FOUND login fail: " + dto.getPlayerUsername();
			loggerSingleton.getAccessLog().warn(invalidUsername);
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
}
