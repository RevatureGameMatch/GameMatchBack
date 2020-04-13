package com.revature.g2g.api.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

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
import com.revature.g2g.api.templates.SurveyTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.handlers.SkillPlayerChangeJTHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;

@CrossOrigin
@RestController
@RequestMapping(value = "Survey")
public class SurveyController {
	@Autowired
	private AuthenticatorHelper authenticatorHelper;
	@Autowired
	private PlayerRoomJTHandler playerRoomJTHandler;
	@Autowired
	private RoomHandler roomHandler;
	@Autowired
	private SkillPlayerChangeJTHandler skillPlayerChangeJTHandler;
	@Autowired
	private SkillGameJTHandler skillGameJTHandler;
	@PostMapping
	public ResponseEntity<Set<Room>> getSurveys(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Set<Room> rooms = playerRoomJTHandler.findSurveyRooms(player);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
	}
	@PostMapping("/Room/Id/{id}")
	public ResponseEntity<List<SurveyTemplate>> getSurvey(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		Room room = roomHandler.findById(id);
		if(room == null || player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if( playerRoomJTHandler.findByPlayerRoom(player, room) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Set<Player> players = playerRoomJTHandler.findPlayers(room);
		Set<Skill> skills = skillGameJTHandler.findByGame(room.getGame());
		Set<SkillPlayerChangeJT> changes = skillPlayerChangeJTHandler.findBy(room,player);
		//TODO parse through players skipping requestere
		//For each player loop through skills adding them to SurveySkillTemplate
		//For each skill check if hash is in changes, if so use the value from changes.
		return null;
	}
}