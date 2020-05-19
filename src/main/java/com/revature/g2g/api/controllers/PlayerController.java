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

import com.revature.g2g.api.templates.SkillValueTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerDTO;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.business.SkillPlayerJTService;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.SkillPlayerJTHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PasswordHelper;

@CrossOrigin
@RestController
@RequestMapping(value="/player")
public class PlayerController {
	PlayerHandler playerHandler;
	PasswordHelper passwordHelper;
	LoggerSingleton loggerSingleton;
	SkillPlayerJTHandler skillPlayerJTHandler;
	SkillPlayerJTService skillPlayerJTService;
	@Autowired
	public PlayerController(PlayerHandler playerHandler, PasswordHelper passwordHelper, LoggerSingleton loggerSingleton,
			SkillPlayerJTHandler skillPlayerJTHandler, SkillPlayerJTService skillPlayerJTService) {
		super();
		this.playerHandler = playerHandler;
		this.passwordHelper = passwordHelper;
		this.loggerSingleton = loggerSingleton;
		this.skillPlayerJTHandler = skillPlayerJTHandler;
		this.skillPlayerJTService = skillPlayerJTService;
	}
	
	// TODO: rework this once Cognito is working.
	@PostMapping
	public ResponseEntity<PlayerDTO> insert(@RequestBody PlayerDTO dto){
		if(dto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		dto = new PlayerDTO(dto);//sanitizes input
		Player usernameCheck = playerHandler.findByPlayerUsername(dto.getPlayerUsername());
		if(usernameCheck != null) {
			String conflictMessage = "PlayerController: conflict username - " + dto.getPlayerUsername().toString();
			String conflictDisplayMessage = "Username Taken";
			loggerSingleton.getAccessLog().trace(conflictMessage);
			dto.setMessage(conflictDisplayMessage);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
		}
		Player emailCheck = playerHandler.findByEmail(dto.getPlayerEmail());
		if(emailCheck!=null) {
			String conflictMessage = "PlayerController: conflict email - " + dto.getPlayerEmail().toString();
			String conflictDisplayMessage = "Email Taken";
			loggerSingleton.getAccessLog().trace(conflictMessage);
			dto.setMessage(conflictDisplayMessage);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(dto);
		}
		Player player = new Player(dto);
		player.setPlayerPassword(passwordHelper.encryptPassword(player.getPlayerPassword()));
		player.setPlayerRole(PlayerRole.PLAYER);
		playerHandler.save(player);
		skillPlayerJTService.addDefaultSkills(player);
		player.setPlayerPassword("****");
		PlayerDTO newTemplate = new PlayerDTO(player);
		String newPlayerMessage = "PlayerController: new player - " + player.toString();
		loggerSingleton.getBusinessLog().trace(newPlayerMessage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(newTemplate);
	}
	
	@GetMapping("/id/{id}/skills")
	public ResponseEntity<List<SkillValueTemplate>> findAllSkillsById(@PathVariable("id") int id){
		Optional<Player> playerOpt = playerHandler.findById(id);
		if(playerOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(processSkills(playerOpt.get()));
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@GetMapping("/username/{username}/skills")
	public ResponseEntity<List<SkillValueTemplate>> findAllSkillsByName(@PathVariable("username") String username){
		Player player = playerHandler.findByPlayerUsername(username);
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
