package com.revature.g2g.api.controllers;

import java.util.ArrayList;
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

import com.revature.g2g.models.Game;
import com.revature.g2g.models.GameDTO;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillDTO;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;
import com.revature.g2g.services.helpers.GameHelper;

@CrossOrigin
@RestController
@RequestMapping(value="game")
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
	public ResponseEntity<List<GameDTO>> getGames(){
		List<Game> games = gameHandler.findAll();
		List<GameDTO> returnThis = new ArrayList<GameDTO>();
		if(games.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			for (Game game : games) {
				returnThis.add(new GameDTO(game) );
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnThis);
		}
	}
	
	@GetMapping("/name/{name}")
	public ResponseEntity<GameDTO> findByName(@PathVariable("name") String name){
		String cleanName = Jsoup.clean(name, Whitelist.none()).replace('_', ' ');
		Game game = gameHandler.findByName(cleanName);
		if(game == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new GameDTO(game) );
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<GameDTO> findById(@PathVariable("id") int id){
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new GameDTO(gameOpt.get()) );
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@GetMapping("/name/{name}/skills")
	public ResponseEntity<List<SkillDTO>> findAllSkillsByName(@PathVariable("name") String name){
		String cleanName = Jsoup.clean(name, Whitelist.none()).replace('_', ' ');
		Game game = gameHandler.findByName(cleanName);
		List<Skill> set = skillGameJTHandler.findByGame(game);
		List<SkillDTO> returnThis = new ArrayList<SkillDTO>();
		if (game == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			for (Skill skill : set) {
				returnThis.add(new SkillDTO(skill) );
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnThis);
		}
	}
	
	@GetMapping("/id/{id}/skills")
	public ResponseEntity<List<SkillDTO>> findAllSkillsById(@PathVariable("id") int id){
		Optional<Game> gameOpt = gameHandler.findById(id);
		if (gameOpt.isPresent()) {
			List<Skill> set = skillGameJTHandler.findByGame(gameOpt.get());
			List<SkillDTO> returnThis = new ArrayList<SkillDTO>();
			for (Skill skill : set) {
				returnThis.add(new SkillDTO(skill) );
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnThis);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	// TODO: While we need sender, this depends upon GameTemplate, which is depreciated
	@PostMapping("")
	public ResponseEntity<List<GameDTO>> insert(@RequestBody GameDTO dto){
		Optional<Game> dataGame = null;
		if(dto == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		} else {
			dataGame = gameHandler.findById(dto.getGameId() );
		}
		Player player = authenticatorHelper.getPlayer(dto.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!(player.getPlayerRole().equals(PlayerRole.ADMIN) || player.getPlayerRole().equals(PlayerRole.MODERATOR))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if(dataGame != null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
		gameHandler.save(gameHelper.clean(new Game(dataGame)) );
		List<Game> games = gameHandler.findAll();
		if(games.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			List<GameDTO> returnThis = new ArrayList<GameDTO>();
			for (Game game : games) {
				returnThis.add(new GameDTO(game) );
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(returnThis);
		}
	}

	@PatchMapping("")
	public ResponseEntity<List<GameDTO>> update(@RequestBody GameDTO gameDTO){
		if(gameDTO == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(gameDTO.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!(player.getPlayerRole().equals(PlayerRole.ADMIN) || player.getPlayerRole().equals(PlayerRole.MODERATOR))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(gameDTO.getGameId());
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Game game = gameOpt.get();
		gameDTO = new GameDTO(gameDTO); //sanitize gameDTO
		game.setName(gameDTO.getName() );
		game.setDescription(gameDTO.getDescription() );
		game.setLink(gameDTO.getLink() );
		gameHandler.save(game);
		List<Game> games = gameHandler.findAll();
		if(games.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			List<GameDTO> returnThis = new ArrayList<GameDTO>();
			for (Game gameElement : games) {
				returnThis.add(new GameDTO(gameElement) );
			}
			return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(returnThis);
		}
	}
}