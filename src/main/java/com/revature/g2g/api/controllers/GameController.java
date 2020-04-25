package com.revature.g2g.api.controllers;

import java.util.List;
import java.util.Optional;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.GameTemplate;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.Skill;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;
import com.revature.g2g.services.helpers.GameHelper;

@CrossOrigin
@RestController
@RequestMapping(value="Game")
public class GameController {
	private GameHandler gameHandler;
	private AuthenticatorHelper authenticatorHelper;
	private GameHelper gameHelper;
	private SkillGameJTHandler skillGameJTHandler;
	@Autowired
	public GameController(GameHandler gameHandler, AuthenticatorHelper authenticatorHelper, GameHelper gameHelper, SkillGameJTHandler skillGameJTHandler) {
		super();
		this.gameHandler = gameHandler;
		this.authenticatorHelper = authenticatorHelper;
		this.gameHelper = gameHelper;
		this.skillGameJTHandler = skillGameJTHandler;
	}
	@GetMapping
	public ResponseEntity<List<Game>> getGames(){
		List<Game> games = gameHandler.findAll();
		if(games.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(games);
		}
	}
	@GetMapping("/Name/{name}")
	public ResponseEntity<Game> findByName(@PathVariable("name") String name){
		String cleanName = Jsoup.clean(name, Whitelist.none()).replace('_', ' ');
		Game game = gameHandler.findByName(cleanName);
		if(game == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(game);
		}
	}
	@GetMapping("/Id/{id}")
	public ResponseEntity<Game> findById(@PathVariable("id") int id){
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(gameOpt.get());
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	@GetMapping("/Name/{name}/Skills")
	public ResponseEntity<List<Skill>> findAllSkillsByName(@PathVariable("name") String name){
		String cleanName = Jsoup.clean(name, Whitelist.none()).replace('_', ' ');
		Game game = gameHandler.findByName(cleanName);
		List<Skill> set = skillGameJTHandler.findByGame(game);
		if (game == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(set);
		}
	}
	@GetMapping("/Id/{id}/Skills")
	public ResponseEntity<List<Skill>> findAllSkillsById(@PathVariable("id") int id){
		Optional<Game> gameOpt = gameHandler.findById(id);
		if (gameOpt.isPresent()) {
			List<Skill> set = skillGameJTHandler.findByGame(gameOpt.get());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(set);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	@PostMapping("")
	public ResponseEntity<List<Game>> insert(@RequestBody GameTemplate gameTemplate){
		if(gameTemplate == null || gameTemplate.getGame() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(gameTemplate.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!(player.getPlayerRole().equals(PlayerRole.ADMIN) || player.getPlayerRole().equals(PlayerRole.MODERATOR))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if(gameHandler.findByName(gameTemplate.getGame().getName()) != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		gameHandler.save(gameHelper.clean(gameTemplate.getGame()));
		List<Game> games = gameHandler.findAll();
		if(games.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(games);
		}
	}

	@PatchMapping("")
	public ResponseEntity<List<Game>> update(@RequestBody GameTemplate gameTemplate){
		if(gameTemplate == null || gameTemplate.getGame() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(gameTemplate.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!(player.getPlayerRole().equals(PlayerRole.ADMIN) || player.getPlayerRole().equals(PlayerRole.MODERATOR))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(gameTemplate.getGame().getGameId());
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Game game = gameOpt.get();
		game.setName(Jsoup.clean(gameTemplate.getGame().getName(), Whitelist.none()));
		game.setDescription(Jsoup.clean(gameTemplate.getGame().getDescription(), Whitelist.none()));
		game.setLink(Jsoup.clean(gameTemplate.getGame().getLink(), Whitelist.none()));
		gameHandler.save(gameTemplate.getGame());
		List<Game> games = gameHandler.findAll();
		if(games.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(games);
		}
	}
}