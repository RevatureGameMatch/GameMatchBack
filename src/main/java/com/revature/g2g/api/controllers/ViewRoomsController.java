package com.revature.g2g.api.controllers;

import java.util.List;

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
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.services.business.PlayerRoomService;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;
import com.revature.g2g.services.helpers.LoggerSingleton;

@CrossOrigin
@RestController
@RequestMapping(value="/Rooms")
public class ViewRoomsController {
	private PlayerRoomService playerRoomService;
	private PlayerHandler playerHandler;
	private LoggerSingleton loggerSingleton;
	private AuthenticatorHelper authenticatorHelper;
	@Autowired
	public ViewRoomsController(PlayerRoomService playerRoomService, PlayerHandler playerHandler,
			LoggerSingleton loggerSingleton, AuthenticatorHelper authenticatorHelper) {
		super();
		this.playerRoomService = playerRoomService;
		this.playerHandler = playerHandler;
		this.loggerSingleton = loggerSingleton;
		this.authenticatorHelper = authenticatorHelper;
	}
	@PostMapping(value="/Casual")
	public ResponseEntity<List<Room>> casual(@RequestBody PlayerTemplate template){
		return getRooms(template, RoomPlayStyle.CASUAL);
	}
	@PostMapping(value="/Hybrid")
	public ResponseEntity<List<Room>> hybrid(@RequestBody PlayerTemplate template){
		return getRooms(template, RoomPlayStyle.HYBRID);
	}
	@PostMapping(value="/Serious")
	public ResponseEntity<List<Room>> serious(@RequestBody PlayerTemplate template){
		return getRooms(template, RoomPlayStyle.SERIOUS);
	}
	private ResponseEntity<List<Room>> getRooms(PlayerTemplate template, RoomPlayStyle style){
		if(template==null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(template);
		List<Room> rooms = playerRoomService.getQualifiedRooms(player, style);
		String logMessage = "ViewRoomsController: qualified rooms requested by: " + template;
		loggerSingleton.getBusinessLog().trace(logMessage);
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
		}
	}
}