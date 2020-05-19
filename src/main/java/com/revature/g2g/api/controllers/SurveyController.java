package com.revature.g2g.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import com.revature.g2g.api.templates.SurveySubmitTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.models.SurveyDTO;
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
@RequestMapping(value = "survey")
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
	@PostMapping("/room/id/{id}/submit")
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
	@PostMapping("/")
	public ResponseEntity<List<SurveyDTO>> getAllSurveysForPlayer(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		List<Room> roomList = playerRoomJTHandler.findSurveyRooms(player).stream().distinct().collect(Collectors.toList());
		if( roomList == null || roomList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		List<SurveyDTO> result = new ArrayList<>();
		for(Room room : roomList) {
			result.add(buildSurveyDTO(room, player));
		}
		return ResponseEntity.ok(result);
	}
	@PostMapping("/room/{id}")
	public ResponseEntity<SurveyDTO> getOneSurvey(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		Optional<Room> roomOpt = roomHandler.findById(id);
		if(!roomOpt.isPresent() || player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Room room = roomOpt.get();
		if( playerRoomJTHandler.findByPlayerAndRoom(player, room) == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		SurveyDTO result = buildSurveyDTO(room, player);
		return ResponseEntity.ok(result);
	}
	private SurveyDTO buildSurveyDTO(Room room, Player player) {
		List<Player> players = playerRoomJTHandler.findPlayers(room).stream().distinct().collect(Collectors.toList());
		List<Skill> skills = skillGameJTHandler.findByGame(room.getGame()).stream().distinct().collect(Collectors.toList());
		players.remove(player);
		SurveyDTO result = new SurveyDTO();
		result.setPlayers(players);
		result.setSkills(skills);
		result.setRoom(room);
		return result;
	}
}