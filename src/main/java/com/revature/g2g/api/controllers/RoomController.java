package com.revature.g2g.api.controllers;

import java.util.Set;

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

import com.revature.g2g.api.templates.RoomTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.Room;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;
import com.revature.g2g.services.helpers.RoomHelper;

@CrossOrigin
@RestController
@RequestMapping(value="Room")
public class RoomController {
	private RoomHandler roomHandler;
	private AuthenticatorHelper authenticatorHelper;
	private RoomHelper roomHelper;
	@Autowired
	public RoomController(RoomHandler roomHandler, AuthenticatorHelper authenticatorHelper, RoomHelper roomHelper) {
		super();
		this.roomHandler = roomHandler;
		this.authenticatorHelper = authenticatorHelper;
		this.roomHelper = roomHelper;
	}
	@GetMapping
	public ResponseEntity<Set<Room>> getRooms(){
		Set<Room> rooms = roomHandler.findAll();
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
		}
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<Room> findById(@PathVariable("id") int id){
		Room room = roomHandler.findById(id);
		if(room == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(room);
		}
	}
	@PostMapping("")
	public ResponseEntity<Set<Room>> insert(@RequestBody RoomTemplate roomTemplate){
		if(roomTemplate == null || roomTemplate.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(roomTemplate.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!(player.getPlayerRole().equals(PlayerRole.ADMIN) || player.getPlayerRole().equals(PlayerRole.MODERATOR))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Room room = roomHelper.clean(roomTemplate.getRoom());
		room.setCurrentPlayers(0);
		roomHandler.insert(room);
		Set<Room> rooms = roomHandler.findAll();
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(rooms);
		}
	}
	@PatchMapping("")
	public ResponseEntity<Set<Room>> update(@RequestBody RoomTemplate roomTemplate){
		if(roomTemplate == null || roomTemplate.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(roomTemplate.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!(player.getPlayerRole().equals(PlayerRole.ADMIN) || player.getPlayerRole().equals(PlayerRole.MODERATOR))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Room room = roomHandler.findById(roomTemplate.getRoom().getRoomId());
		if(room == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		roomHelper.ammend(room, roomTemplate.getRoom());
		roomHandler.update(roomTemplate.getRoom());
		Set<Room> rooms = roomHandler.findAll();
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(rooms);
		}
	}
}