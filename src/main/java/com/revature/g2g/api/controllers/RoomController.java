package com.revature.g2g.api.controllers;

import java.util.List;
import java.util.Optional;

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

import com.revature.g2g.api.templates.DiscordInviteTemplate;
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
import com.revature.g2g.services.jda.helpers.GuildHelper;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Invite;

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
	private GuildHelper guildHelper;
	@Autowired
	public void setRoomHandler(RoomHandler roomHandler) {
		this.roomHandler = roomHandler;
	}
	@Autowired
	public void setAuthenticatorHelper(AuthenticatorHelper authenticatorHelper) {
		this.authenticatorHelper = authenticatorHelper;
	}
	@Autowired
	public void setRoomHelper(RoomHelper roomHelper) {
		this.roomHelper = roomHelper;
	}
	@Autowired
	public void setPlayerRoomJTHandler(PlayerRoomJTHandler playerRoomJTHandler) {
		this.playerRoomJTHandler = playerRoomJTHandler;
	}
	@Autowired
	public void setPlayerRoomService(PlayerRoomService playerRoomService) {
		this.playerRoomService = playerRoomService;
	}
	@Autowired
	public void setLoggerSingleton(LoggerSingleton loggerSingleton) {
		this.loggerSingleton = loggerSingleton;
	}
	@Autowired
	public void setGameHandler(GameHandler gameHandler) {
		this.gameHandler = gameHandler;
	}
	@Autowired
	public void setGuildHelper(GuildHelper guildHelper) {
		this.guildHelper = guildHelper;
	}
	@GetMapping
	public ResponseEntity<List<Room>> getRooms(){
		List<Room> rooms = roomHandler.findAll();
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(rooms);
		}
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<Room> findById(@PathVariable("id") int id){
		Optional<Room> roomOpt = roomHandler.findById(id);
		if(roomOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(roomOpt.get());
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	@PostMapping("")
	public ResponseEntity<Room> insert(@RequestBody RoomTemplate roomTemplate){
		if(roomTemplate == null || roomTemplate.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(roomTemplate.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Room room = roomHelper.clean(roomTemplate.getRoom());
		room.setCurrentPlayers(0);
		roomHandler.save(room);
		return ResponseEntity.status(HttpStatus.CREATED).body(room);
	}
	@PatchMapping("")
	public ResponseEntity<List<Room>> update(@RequestBody RoomTemplate roomTemplate){
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
		Optional<Room> roomOpt = roomHandler.findById(roomTemplate.getRoom().getRoomId());
		if(!roomOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Room room = roomOpt.get();
		roomHelper.ammend(room, roomTemplate.getRoom());
		roomHandler.save(roomTemplate.getRoom());
		List<Room> rooms = roomHandler.findAll();
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(rooms);
		}
	}
	@PostMapping("/Player")
	public ResponseEntity<DiscordInviteTemplate> insert(@RequestBody PlayerRoomTemplate template){
		if(template == null || template.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(template.getSender());
		if(player == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Room> roomOpt = roomHandler.findById(template.getRoom().getRoomId());
		if(!roomOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		Room room = roomOpt.get();
		PlayerRoomJT alreadyInRoom = playerRoomJTHandler.findByPlayerAndRoom(player, room);
		Invite invite = roomHelper.getInvite(room,player);
		if(alreadyInRoom == null) {
			room.setCurrentPlayers(room.getCurrentPlayers() + 1);
			roomHandler.save(room);
		}
		DiscordInviteTemplate discordInviteTemplate = new DiscordInviteTemplate();
		discordInviteTemplate.setChannelId(room.getDiscordVoiceChannelId());
		discordInviteTemplate.setChannelName(room.getName());
		Guild guild = guildHelper.getGuild();
		discordInviteTemplate.setGuildId(guild.getIdLong());
		discordInviteTemplate.setGuildName(guild.getName());
		discordInviteTemplate.setInviteCode(invite.getCode());
		discordInviteTemplate.setUrlWeb(invite.getUrl());
		discordInviteTemplate.setUrlApp("discord://discordapp.com/invite/" + invite.getCode());
		return ResponseEntity.status(HttpStatus.CREATED).body(discordInviteTemplate);
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
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.CASUAL, gameOpt.get());
	}
	@PostMapping(value="/Game/Hybrid/{id}")
	public ResponseEntity<List<Room>> HybridName(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.HYBRID, gameOpt.get());
	}
	@PostMapping(value="/Game/Serious/{id}")
	public ResponseEntity<List<Room>> SeriousName(@Valid @RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.SERIOUS, gameOpt.get());
	}
	@PostMapping(value="/Game/Id/{id}")
	public ResponseEntity<List<Room>> name(@RequestBody PlayerTemplate template, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(template);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, gameOpt.get());
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