package com.revature.g2g.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	private PlayerHandler playerHandler;
	private PlayerRoomJTHandler playerRoomJTHandler;
	private RoomHandler roomHandler;
	private SkillHandler skillHandler;
	private SkillPlayerChangeJTHandler skillPlayerChangeJTHandler;
	private SkillGameJTHandler skillGameJTHandler;
	private SurveyService surveyService;
	@Autowired
	public void setAuthenticatorHelper(AuthenticatorHelper authenticatorHelper) {
		this.authenticatorHelper = authenticatorHelper;
	}
	@Autowired
	public void setPlayerHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler;
	}
	@Autowired
	public void setPlayerRoomJTHandler(PlayerRoomJTHandler playerRoomJTHandler) {
		this.playerRoomJTHandler = playerRoomJTHandler;
	}
	@Autowired
	public void setRoomHandler(RoomHandler roomHandler) {
		this.roomHandler = roomHandler;
	}
	@Autowired
	public void setSkillHandler(SkillHandler skillHandler) {
		this.skillHandler = skillHandler;
	}
	@Autowired
	public void setSkillPlayerChangeJTHandler(SkillPlayerChangeJTHandler skillPlayerChangeJTHandler) {
		this.skillPlayerChangeJTHandler = skillPlayerChangeJTHandler;
	}
	@Autowired
	public void setSkillGameJTHandler(SkillGameJTHandler skillGameJTHandler) {
		this.skillGameJTHandler = skillGameJTHandler;
	}
	@Autowired
	public void setSurveyService(SurveyService surveyService) {
		this.surveyService = surveyService;
	}
	@PostMapping
	public ResponseEntity<List<Room>> getSurveys(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		List<Room> rooms = playerRoomJTHandler.findSurveyRooms(player);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
	}
	@PostMapping("/Player")
	public ResponseEntity<List<SurveyRoomTemplate>> getSurveysForPlayer(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if( playerRoomJTHandler.findByPlayer(player) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		List<SurveyRoomTemplate> bodyList = new ArrayList<>();
		List<Room> roomList = new ArrayList<>();
		List<Room> surveyRoomList = playerRoomJTHandler.findSurveyRooms(player);
		roomList.addAll(surveyRoomList);
		for(Room r: roomList) {
			List<SurveyTemplate> surveyTemplateList = templateGeneratingLoop(r, player);
			SurveyRoomTemplate surveyRoomTemplate = new SurveyRoomTemplate(r, surveyTemplateList);
			bodyList.add(surveyRoomTemplate);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(bodyList);
	}
	public List<SurveyTemplate> templateGeneratingLoop(Room room, Player player){
		List<Player> players = playerRoomJTHandler.findPlayers(room);
		List<Skill> skills = skillGameJTHandler.findByGame(room.getGame());
		List<SurveyTemplate> surveyTemplateList = new ArrayList<>();
		List<SkillPlayerChangeJT> skillPlayerChangeJTList = skillPlayerChangeJTHandler.findBy(room, player);
		SurveySkillTemplate[] arr = new SurveySkillTemplate[skills.size()];
		for (Player p: players) {
			if(p.equals(player)) {
				continue;
			}
			ArrayList<SurveySkillTemplate> surveySkillTemplateArray = skillGeneratingLoop(arr, skills, p, player, room, skillPlayerChangeJTList);
			PlayerTemplate playerTemplate = new PlayerTemplate(p);
			SurveyTemplate surveyTemplate = new SurveyTemplate(playerTemplate, surveySkillTemplateArray.toArray(arr));
			
			surveyTemplateList.add(surveyTemplate);
		}
		return surveyTemplateList;
	}
	@PostMapping("/Room/Id/{id}")
	public ResponseEntity<List<SurveyTemplate>> getSurvey(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		Optional<Room> roomOpt = roomHandler.findById(id);
		if(!roomOpt.isPresent() || player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Room room = roomOpt.get();
		if( playerRoomJTHandler.findByPlayerAndRoom(player, room) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		List<Player> players = playerRoomJTHandler.findPlayers(room);
		List<Skill> skills = skillGameJTHandler.findByGame(room.getGame());
		List<SurveyTemplate> surveyTemplateList = new ArrayList<>();
		List<SkillPlayerChangeJT> skillPlayerChangeJTList = skillPlayerChangeJTHandler.findBy(room, player);//checks room and modified by
		
		SurveySkillTemplate[] arr = new SurveySkillTemplate[skills.size()];
		for (Player p: players) {
			if(p.equals(player)) {
				continue;
			}
			ArrayList<SurveySkillTemplate> surveySkillTemplateArray = skillGeneratingLoop(arr, skills, p, player, room, skillPlayerChangeJTList);
			PlayerTemplate playerTemplate = new PlayerTemplate(p);
			SurveyTemplate surveyTemplate = new SurveyTemplate(playerTemplate, surveySkillTemplateArray.toArray(arr));
			
			surveyTemplateList.add(surveyTemplate);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(surveyTemplateList);
	}
	private ArrayList<SurveySkillTemplate> skillGeneratingLoop(SurveySkillTemplate[] arr, List<Skill> skills, Player p, Player modifiedBy, 
			Room room, List<SkillPlayerChangeJT> skillPlayerChangeJTList) {
		ArrayList<SurveySkillTemplate> surveySkillTemplateArray = new ArrayList<>();
		int size = skillPlayerChangeJTList.size();
		Game game = room.getGame();
		for (Skill s: skills) {
			boolean found = false;
			if (size != 0) {
				for (SkillPlayerChangeJT spc: skillPlayerChangeJTList) {
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
		Optional<Player> playerOpt = playerHandler.findById(template.getPlayer().getPlayerId());
		Optional<Room> roomOpt = roomHandler.findById(roomId);
		Optional<Skill> skillOpt = skillHandler.findById(template.getSkill().getSkillId());
		if(!roomOpt.isPresent() || !playerOpt.isPresent() || modifiedBy == null || !skillOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = playerOpt.get();
		Room room = roomOpt.get();
		Skill skill = skillOpt.get();
		if( playerRoomJTHandler.findByPlayerAndRoom(player, room) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		SkillPlayerChangeJT skillPlayerChangeJT = skillPlayerChangeJTHandler.findBy(modifiedBy, player, room, skill);
		if(skillPlayerChangeJT != null) {
			String message = "You have already ranked " + skillPlayerChangeJT.getPlayer().getPlayerUsername() + "'s skill in " +
					skillPlayerChangeJT.getSkillPlayerJT().getSkill().getName() +  " for this game.";
			MessageTemplate messageTemplate = new MessageTemplate(message);
			return ResponseEntity.status(HttpStatus.CONFLICT).body(messageTemplate);
		}
		skillPlayerChangeJT = surveyService.submit(modifiedBy, player, room, skill, template.getValue());
		String successMessage = "Thank you for ranking " + skillPlayerChangeJT.getPlayer().getPlayerUsername() + "'s skill in " +
				skillPlayerChangeJT.getSkillPlayerJT().getSkill().getName() + ". Together we will build a brighter tomorrow.";
		MessageTemplate successTemplate = new MessageTemplate(successMessage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(successTemplate);
	}
}