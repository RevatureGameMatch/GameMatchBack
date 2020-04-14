package com.revature.g2g.api.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.PlayerTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
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
	@Autowired
	SkillPlayerJTHandler skillPlayerJTHandler;
	@PostMapping
	public ResponseEntity<PlayerTemplate> insert(@RequestBody PlayerTemplate template){
		if(template==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		System.out.println(template);
		Player usernameCheck = playerHandler.findByUsername(template.getPlayerUsername());
		if(usernameCheck!=null) {
			template.setPlayerPassword("****");
			String conflictMessage = "PlayerController: conflict username - " + template.toString();
			String conflictDisplayMessage = "Username Taken";
			loggerSingleton.getAccessLog().trace(conflictMessage);
			template.setMessage(conflictDisplayMessage);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(template);
		}
		Player emailCheck = playerHandler.findByEmail(template.getPlayerEmail());
		if(emailCheck!=null) {
			template.setPlayerPassword("****");
			String conflictMessage = "PlayerController: conflict email - " + template.toString();
			String conflictDisplayMessage = "Username Taken";
			loggerSingleton.getAccessLog().trace(conflictMessage);
			template.setMessage(conflictDisplayMessage);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(template);
		}
		Player player = new Player(template);
		player.setPlayerPassword(passwordHelper.encryptPassword(player.getPlayerPassword()));
		player.setPlayerRole(PlayerRole.PLAYER);
		playerHandler.insert(player);
		player.setPlayerPassword("****");
		PlayerTemplate newTemplate = new PlayerTemplate(player);
		String newPlayerMessage = "PlayerController: new player - " + player.toString();
		loggerSingleton.getBusinessLog().trace(newPlayerMessage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newTemplate);
	}
	@PostMapping("/Id/{id}/Skills")
	public ResponseEntity<Set<Skill>> findAllSkillsById(@PathVariable("id") int id){
		Player player = playerHandler.findById(id);
		Set<Skill> set = skillPlayerJTHandler.findPlayerSkills(player);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(set);
		}
	}
	@PostMapping("/Username/{username}/Skills")
	public ResponseEntity<Set<Skill>> findAllSkillsByName(@PathVariable("username") String username){
		Player player = playerHandler.findByUsername(username);
		System.out.println(player);
		Set<Skill> set = skillPlayerJTHandler.findPlayerSkills(player);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(set);
		}
	}
	
}
