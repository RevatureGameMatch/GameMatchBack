package com.revature.g2g.api.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

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

import com.revature.g2g.api.templates.PlayerRoomTemplate;
import com.revature.g2g.api.templates.PlayerTemplate;
import com.revature.g2g.api.templates.RoomTemplate;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.services.business.PlayerRoomService;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.PlayerRoomJTHandler;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.helpers.AuthenticatorHelper;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.RoomHelper;

@CrossOrigin
@RestController
@RequestMapping(value="Room")
public class RoomController {
	private RoomHandler roomHandler;
	private AuthenticatorHelper authenticatorHelper;
	private RoomHelper roomHelper;
	private PlayerRoomJTHandler playerRoomJTHandler;
	private PlayerRoomService playerRoomService;
	private LoggerSingleton loggerSingleton;
	private GameHandler gameHandler;
	@Autowired
	public RoomController(RoomHandler roomHandler, AuthenticatorHelper authenticatorHelper, RoomHelper roomHelper,
			PlayerRoomJTHandler playerRoomJTHandler, PlayerRoomService playerRoomService,
			LoggerSingleton loggerSingleton, GameHandler gameHandler) {
		super();
		this.roomHandler = roomHandler;
		this.authenticatorHelper = authenticatorHelper;
		this.roomHelper = roomHelper;
		this.playerRoomJTHandler = playerRoomJTHandler;
		this.playerRoomService = playerRoomService;
		this.loggerSingleton = loggerSingleton;
		this.gameHandler = gameHandler;
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
	@PostMapping("/Player")
	public ResponseEntity<String> insert(@RequestBody PlayerRoomTemplate template){
		if(template == null || template.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(template.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Room room = roomHandler.findById(template.getRoom().getRoomId());
		if(room == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		PlayerRoomJT alreadyInRoom = playerRoomJTHandler.findByPlayerRoom(player, room);
		String inviteUrl = roomHelper.getInvite(room,player);
		if(alreadyInRoom == null) {
			room.setCurrentPlayers(room.getCurrentPlayers() + 1);
			roomHandler.update(room);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(inviteUrl);
	}
	@PostMapping(value="/Style/Casual")
	public ResponseEntity<List<Room>> casual(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return getRooms(player, RoomPlayStyle.CASUAL);
	}
	@PostMapping(value="/Style/Hybrid")
	public ResponseEntity<List<Room>> hybrid(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return getRooms(player, RoomPlayStyle.HYBRID);
	}
	@PostMapping(value="/Style/Serious")
	public ResponseEntity<List<Room>> serious(@Valid @RequestBody PlayerTemplate template){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return getRooms(player, RoomPlayStyle.SERIOUS);
	}
	@PostMapping(value="/Game/Casual/{id}")
	public ResponseEntity<List<Room>> casualName(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Game game = gameHandler.findById(id);
		if(game==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.CASUAL, game);
	}
	@PostMapping(value="/Game/Hybrid/{id}")
	public ResponseEntity<List<Room>> HybridName(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Game game = gameHandler.findById(id);
		if(game==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.HYBRID, game);
	}
	@PostMapping(value="/Game/Serious/{id}")
	public ResponseEntity<List<Room>> SeriousName(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Game game = gameHandler.findById(id);
		if(game==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.SERIOUS, game);
	}
	@PostMapping(value="/Game/Id/{id}")
	public ResponseEntity<List<Room>> name(@RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Game game = gameHandler.findById(id);
		if(game==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, game);
	}
	private ResponseEntity<List<Room>> getRooms(Player player, RoomPlayStyle style){
		List<Room> rooms = playerRoomService.getQualifiedRooms(player, style);
		String logMessage = "ViewRoomsController1: qualified rooms requested by: " + player.getPlayerId() + " " + player.getPlayerUsername();
		loggerSingleton.getBusinessLog().trace(logMessage);
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
		}
	}
	private ResponseEntity<List<Room>> getRooms(Player player, Game game){
		List<Room> rooms = playerRoomService.getQualifiedRooms(player, game);
		String logMessage = "ViewRoomsController2: qualified rooms requested by: " + player.getPlayerId() + " " + player.getPlayerUsername();
		loggerSingleton.getBusinessLog().trace(logMessage);
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
		}
	}
	private ResponseEntity<List<Room>> getRooms(Player player, RoomPlayStyle style, Game game){
		List<Room> rooms = playerRoomService.getQualifiedRooms(player, style, game);
		String logMessage = "ViewRoomsController3: qualified rooms requested by: " + player.getPlayerId() + " " + player.getPlayerUsername();
		loggerSingleton.getBusinessLog().trace(logMessage);
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
		}
	}
}