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
import com.revature.g2g.api.templates.SurveySkillTemplate;
import com.revature.g2g.api.templates.SurveySubmitTemplate;
import com.revature.g2g.api.templates.SurveyTemplate;
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
	private PlayerRoomJTHandler playerRoomJTHandler;
	private RoomHandler roomHandler;
	private SkillPlayerChangeJTHandler skillPlayerChangeJTHandler;
	private SkillGameJTHandler skillGameJTHandler;
	private PlayerHandler playerHandler;
	private SkillHandler skillHandler;
	private SurveyService surveyService;
	@Autowired
	public void setAuthenticatorHelper(AuthenticatorHelper authenticatorHelper) {
		this.authenticatorHelper = authenticatorHelper;
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
	public void setSkillPlayerChangeJTHandler(SkillPlayerChangeJTHandler skillPlayerChangeJTHandler) {
		this.skillPlayerChangeJTHandler = skillPlayerChangeJTHandler;
	}
	@Autowired
	public void setSkillGameJTHandler(SkillGameJTHandler skillGameJTHandler) {
		this.skillGameJTHandler = skillGameJTHandler;
	}
	@Autowired
	public void setPlayerHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler;
	}
	@Autowired
	public void setSkillHandler(SkillHandler skillHandler) {
		this.skillHandler = skillHandler;
	}
	@Autowired
	public void setSurveyHelper(SurveyService surveyService) {
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
		System.out.println(skills);
		Set<SurveyTemplate> surveyTemplateSet = new HashSet<>();
		Set<SkillPlayerChangeJT> skillPlayerChangeJTSet = skillPlayerChangeJTHandler.findBy(room, player);//checks room and modified by
		
		SurveySkillTemplate[] arr = new SurveySkillTemplate[skills.size()];
		for (Player p: players) {
			if(p.equals(player)) {
//				players.remove(p);
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
		System.out.println(skillPlayerChangeJTSet);
		ArrayList<SurveySkillTemplate> surveySkillTemplateArray = new ArrayList<>();
		int size = skillPlayerChangeJTSet.size();
		for (Skill s: skills) {
			System.out.println(s);
			if (size != 0) {
				for (SkillPlayerChangeJT spc: skillPlayerChangeJTSet) {
					System.out.println("this is the spc" + spc);
					if(spc.getSkillPlayerJT().getSkill().equals(s) && spc.getPlayer().equals(p)) {
						double val = spc.getValue();
						System.out.println("this is the value" + val);
						SurveySkillTemplate surveySkillTemplate = new SurveySkillTemplate(
								s, (float) val);
						surveySkillTemplateArray.add(surveySkillTemplate);
						System.out.println("this is the survey skill template" + surveySkillTemplate);
					} else {
						SurveySkillTemplate surveySkillTemplate = new SurveySkillTemplate(
								s, 0);
						surveySkillTemplateArray.add(surveySkillTemplate);
						System.out.println(surveySkillTemplate);
					}
				}
			}
		}
		System.out.println(surveySkillTemplateArray);
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