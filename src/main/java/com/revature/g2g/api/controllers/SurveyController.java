package com.revature.g2g.api.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.PlayerTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;

@CrossOrigin
@RestController
@RequestMapping(value = "Survey")
public class SurveyController {
	@Autowired
	private AuthenticatorHelper authenticatorHelper;
	@Autowired
	private PlayerRoomJTHandler playerRoomJTHandler;
	@PostMapping
	public ResponseEntity<Set<Room>> getSurveys(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Set<Room> rooms = playerRoomJTHandler.findSurveyRooms(player);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
	}
}