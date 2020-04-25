package com.revature.g2g.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.PlayerTemplate;
import com.revature.g2g.api.templates.SkillValueTemplate;
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
	PlayerHandler playerHandler;
	PasswordHelper passwordHelper;
	LoggerSingleton loggerSingleton;
	SkillPlayerJTHandler skillPlayerJTHandler;
	@Autowired
	public PlayerController(PlayerHandler playerHandler, PasswordHelper passwordHelper, LoggerSingleton loggerSingleton,
			SkillPlayerJTHandler skillPlayerJTHandler) {
		super();
		this.playerHandler = playerHandler;
		this.passwordHelper = passwordHelper;
		this.loggerSingleton = loggerSingleton;
		this.skillPlayerJTHandler = skillPlayerJTHandler;
	}
	@PostMapping
	public ResponseEntity<PlayerTemplate> insert(@RequestBody PlayerTemplate template){
		if(template==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		template = new PlayerTemplate(template);//sanitizes input
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
			String conflictDisplayMessage = "Email Taken";
			loggerSingleton.getAccessLog().trace(conflictMessage);
			template.setMessage(conflictDisplayMessage);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(template);
		}
		Player player = new Player(template);
		player.setPlayerPassword(passwordHelper.encryptPassword(player.getPlayerPassword()));
		player.setPlayerRole(PlayerRole.PLAYER);
		playerHandler.save(player);
		player.setPlayerPassword("****");
		PlayerTemplate newTemplate = new PlayerTemplate(player);
		String newPlayerMessage = "PlayerController: new player - " + player.toString();
		loggerSingleton.getBusinessLog().trace(newPlayerMessage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newTemplate);
	}
	@GetMapping("/Id/{id}/Skills")
	public ResponseEntity<List<SkillValueTemplate>> findAllSkillsById(@PathVariable("id") int id){
		Optional<Player> playerOpt = playerHandler.findById(id);
		if(playerOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(processSkills(playerOpt.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	@GetMapping("/Username/{username}/Skills")
	public ResponseEntity<List<SkillValueTemplate>> findAllSkillsByName(@PathVariable("username") String username){
		Player player = playerHandler.findByUsername(username);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(processSkills(player));
		}
	}
	private List<SkillValueTemplate> processSkills(Player player){
		List<Skill> set = skillPlayerJTHandler.findPlayerSkills(player);
		List<SkillValueTemplate> skills = new ArrayList<>();
		for(Skill skill : set) {
			double value = skillPlayerJTHandler.findValue(player, skill);
			skills.add(new SkillValueTemplate(skill, value));
		}
		return skills;
	}
}
