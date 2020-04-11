package com.revature.g2g.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.PlayerTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PasswordHelper;

@CrossOrigin
@RestController
@RequestMapping(value="/Player")
public class PlayerController {
	@Autowired
	PlayerHandler playerHandler;
	@Autowired
	PasswordHelper passwordHelper;
	@Autowired
	LoggerSingleton loggerSingleton;
	@PostMapping
	public ResponseEntity<Player> insert(@RequestBody PlayerTemplate template){
		if(template==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		System.out.println(template);
		Player usernameCheck = playerHandler.findByUsername(template.getPlayerUsername());
		if(usernameCheck!=null) {
			template.setPlayerPassword("****");
			String conflictMessage = "PlayerController: conflict - " + template.toString();
			loggerSingleton.getAccessLog().trace(conflictMessage);
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		Player player = new Player(template);
		player.setPlayerPassword(passwordHelper.encryptPassword(player.getPlayerPassword()));
		player.setPlayerRole(PlayerRole.PLAYER);
		playerHandler.insert(player);
		player.setPlayerPassword("****");
		String newPlayerMessage = "PlayerController: new player - " + player.toString();
		loggerSingleton.getBusinessLog().trace(newPlayerMessage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(player);
	}
}
