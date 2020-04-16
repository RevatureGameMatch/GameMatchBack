package com.revature.g2g.api.controllers;

import java.util.ArrayList;
import java.util.HashSet;
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

import com.revature.g2g.api.templates.MessageTemplate;
import com.revature.g2g.api.templates.PlayerTemplate;
import com.revature.g2g.api.templates.SurveyRoomTemplate;
import com.revature.g2g.api.templates.SurveySkillTemplate;
import com.revature.g2g.api.templates.SurveySubmitTemplate;
import com.revature.g2g.api.templates.SurveyTemplate;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.services.business.SurveyService;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillPlayerChangeJTHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;

@CrossOrigin
@RestController
@RequestMapping(value = "Survey")
public class SurveyController {
	private AuthenticatorHelper authenticatorHelper;
	private PlayerRoomJTHandler playerRoomJTHandler;
	private RoomHandler roomHandler;
	private SkillPlayerChangeJTHandler skillPlayerChangeJTHandler;
	private SkillGameJTHandler skillGameJTHandler;
	private PlayerHandler playerHandler;
	private SkillHandler skillHandler;
	private SurveyService surveyService;
	@Autowired
	public SurveyController(AuthenticatorHelper authenticatorHelper, PlayerRoomJTHandler playerRoomJTHandler, RoomHandler roomHandler,
			SkillPlayerChangeJTHandler skillPlayerChangeJTHandler, SkillGameJTHandler skillGameJTHandler, PlayerHandler playerHandler,
			SkillHandler skillHandler, SurveyService surveyService) {
		this.authenticatorHelper = authenticatorHelper;
		this.playerRoomJTHandler = playerRoomJTHandler;
		this.roomHandler = roomHandler;
		this.skillPlayerChangeJTHandler = skillPlayerChangeJTHandler;
		this.skillGameJTHandler = skillGameJTHandler;
		this.playerHandler = playerHandler;
		this.skillHandler = skillHandler;
		this.surveyService = surveyService;
	}
	@PostMapping
	public ResponseEntity<Set<Room>> getSurveys(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Set<Room> rooms = playerRoomJTHandler.findSurveyRooms(player);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
	}
	@PostMapping("/Player")
	public ResponseEntity<Set<SurveyRoomTemplate>> getSurveysForPlayer(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		Set<PlayerRoomJT> set = playerRoomJTHandler.findByPlayer(player);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if( playerRoomJTHandler.findByPlayer(player) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Set<SurveyRoomTemplate> bodySet = new HashSet<>();
		Set<Room> roomSet = new HashSet<>();
		for (PlayerRoomJT prjt: set) {
			Room room = prjt.getRoom();
			if(!roomSet.contains(room)) {
				roomSet.add(room);
			} 
		}
		for(Room r: roomSet) {
			Set<SurveyTemplate> surveyTemplateSet = templateGeneratingLoop(r, player);
			SurveyRoomTemplate surveyRoomTemplate = new SurveyRoomTemplate(r, surveyTemplateSet);
			bodySet.add(surveyRoomTemplate);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(bodySet);
	}
	public Set<SurveyTemplate> templateGeneratingLoop(Room room, Player player){
		Set<Player> players = playerRoomJTHandler.findPlayers(room);
		Set<Skill> skills = skillGameJTHandler.findByGame(room.getGame());
		Set<SurveyTemplate> surveyTemplateSet = new HashSet<>();
		Set<SkillPlayerChangeJT> skillPlayerChangeJTSet = skillPlayerChangeJTHandler.findBy(room, player);
		SurveySkillTemplate[] arr = new SurveySkillTemplate[skills.size()];
		for (Player p: players) {
			if(p.equals(player)) {
				continue;
			}
			ArrayList<SurveySkillTemplate> surveySkillTemplateArray = skillGeneratingLoop(arr, skills, p, player, room, skillPlayerChangeJTSet);
			PlayerTemplate playerTemplate = new PlayerTemplate(p);
			SurveyTemplate surveyTemplate = new SurveyTemplate(playerTemplate, surveySkillTemplateArray.toArray(arr));
			
			surveyTemplateSet.add(surveyTemplate);
		}
		return surveyTemplateSet;
	}
	@PostMapping("/Room/Id/{id}")
	public ResponseEntity<Set<SurveyTemplate>> getSurvey(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
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
		Set<SurveyTemplate> surveyTemplateSet = new HashSet<>();
		Set<SkillPlayerChangeJT> skillPlayerChangeJTSet = skillPlayerChangeJTHandler.findBy(room, player);//checks room and modified by
		
		SurveySkillTemplate[] arr = new SurveySkillTemplate[skills.size()];
		for (Player p: players) {
			if(p.equals(player)) {
				continue;
			}
			ArrayList<SurveySkillTemplate> surveySkillTemplateArray = skillGeneratingLoop(arr, skills, p, player, room, skillPlayerChangeJTSet);
			PlayerTemplate playerTemplate = new PlayerTemplate(p);
			SurveyTemplate surveyTemplate = new SurveyTemplate(playerTemplate, surveySkillTemplateArray.toArray(arr));
			
			surveyTemplateSet.add(surveyTemplate);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(surveyTemplateSet);
	}
	private ArrayList<SurveySkillTemplate> skillGeneratingLoop(SurveySkillTemplate[] arr, Set<Skill> skills, Player p, Player modifiedBy, 
			Room room, Set<SkillPlayerChangeJT> skillPlayerChangeJTSet) {
		ArrayList<SurveySkillTemplate> surveySkillTemplateArray = new ArrayList<>();
		int size = skillPlayerChangeJTSet.size();
		Game game = room.getGame();
		for (Skill s: skills) {
			boolean found = false;
			if (size != 0) {
				for (SkillPlayerChangeJT spc: skillPlayerChangeJTSet) {
					if(spc.getSkillPlayerJT().getSkill().equals(s) && spc.getPlayer().equals(p)) {
						double val = spc.getValue();
						SurveySkillTemplate surveySkillTemplate = new SurveySkillTemplate(
								s, (val * 100), game);
						surveySkillTemplateArray.add(surveySkillTemplate);
						found = true;
						break;
					}
				}
			}
			if (!found) {
				SurveySkillTemplate surveySkillTemplate = new SurveySkillTemplate(
						s, 0, game);
				surveySkillTemplateArray.add(surveySkillTemplate);	
			}
		}
		return surveySkillTemplateArray;
	}
	@PostMapping("/Room/Id/{id}/Submit")
	public ResponseEntity<MessageTemplate> insertSurvey(@Valid @RequestBody SurveySubmitTemplate template, @PathVariable("id") int roomId){
		Player modifiedBy = authenticatorHelper.getPlayer(template.getModifiedBy());
		Player player = playerHandler.findById(template.getPlayer().getPlayerId());
		Room room = roomHandler.findById(roomId);
		Skill skill = skillHandler.findById(template.getSkill().getSkillId());
		if(room == null || player == null || modifiedBy == null || skill == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if( playerRoomJTHandler.findByPlayerRoom(player, room) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		SkillPlayerChangeJT skillPlayerChangeJT = skillPlayerChangeJTHandler.findBy(modifiedBy, player, room, skill);
		if(skillPlayerChangeJT != null) {
			String message = "You have already ranked " + skillPlayerChangeJT.getPlayer().getPlayerUsername() + "'s skill in " +
					skillPlayerChangeJT.getSkillPlayerJT().getSkill().getName() +  " for this game.";
			MessageTemplate messageTemplate = new MessageTemplate(message);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(messageTemplate);
		}
		surveyService.submit(modifiedBy, player, room, skill, template.getValue());
		String successMessage = "Thank you for ranking this person.";
		MessageTemplate successTemplate = new MessageTemplate(successMessage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(successTemplate);
	}
}