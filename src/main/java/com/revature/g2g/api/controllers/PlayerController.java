package com.revature.g2g.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.PasswordHelper;

@RestController
@RequestMapping(value="/Player")
public class PlayerController {
	@Autowired
	PlayerHandler playerHandler;
	@Autowired
	PasswordHelper passwordHelper;
	@PostMapping
	public ResponseEntity<Player> insert(@RequestBody Player player){
		if(player==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player usernameCheck = playerHandler.findByUsername(player.getPlayerUsername());
		if(usernameCheck==null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		player.setPlayerPassword(passwordHelper.encryptPassword(player.getPlayerPassword()));
		player.setPlayerRole(PlayerRole.PLAYER);
		playerHandler.insert(player);
		player.setPlayerPassword("****");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(player);
	}
}
