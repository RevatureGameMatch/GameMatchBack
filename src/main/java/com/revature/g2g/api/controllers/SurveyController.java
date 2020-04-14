package com.revature.g2g.api.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
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
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.handlers.SkillPlayerChangeJTHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;
import com.revature.g2g.services.helpers.SurveyHelper;

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
	@Autowired
	private PlayerHandler playerHandler;
	@Autowired
	private SkillHandler skillHandler;
	@Autowired
	private SurveyHelper surveyHelper;
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
		ArrayList<SurveySkillTemplate> surveySkillTemplateArray = new ArrayList<>();
		Set<SurveyTemplate> surveyTemplateSet = new HashSet<>();
		//removes player from the set
//		while(players.iterator().hasNext()) {
//			if (players.iterator().next().equals(player)) {
//				players.remove(player);	
//			}
//		}
		//For each player loop through skills adding them to SurveySkillTemplate
		//For each skill check if hash is in changes, if so use the value from changes.
		SurveySkillTemplate[] arr = new SurveySkillTemplate[skills.size()];
		Set<SkillPlayerChangeJT> skillPlayerChangeJTSet = skillPlayerChangeJTHandler.findBy(room, player);
		
		for (Player p: players) {
			if(p.equals(player)) {
				players.remove(p);
			}
			for (Skill s: skills) {
				for (SkillPlayerChangeJT spc: skillPlayerChangeJTSet) {
					int hash = Objects.hash(player, p, room, s);
					int hash2 = spc.hashCode();
					if (hash != hash2) {
						SurveySkillTemplate surveySkillTemplate = new SurveySkillTemplate(
								s, 0);
						surveySkillTemplateArray.add(surveySkillTemplate);
					} else {
						double val = spc.getValue();
						SurveySkillTemplate surveySkillTemplate = new SurveySkillTemplate(
								s, (float) val);
						surveySkillTemplateArray.add(surveySkillTemplate);
					}
				}
			}
			SurveyTemplate surveyTemplate = new SurveyTemplate(p, surveySkillTemplateArray.toArray(arr));
			surveyTemplateSet.add(surveyTemplate);
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(surveyTemplateSet);
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
		surveyHelper.submit(modifiedBy, player, room, skill, template.getValue());
		String successMessage = "Thank you for ranking this person.";
		MessageTemplate successTemplate = new MessageTemplate(successMessage);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(successTemplate);
	}
}