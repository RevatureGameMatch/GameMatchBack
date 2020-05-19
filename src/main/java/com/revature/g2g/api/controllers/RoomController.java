package com.revature.g2g.api.controllers;

import java.util.ArrayList;
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

import com.revature.g2g.models.DiscordInviteDTO;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerDTO;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.models.PlayerRoomDTO;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomDTO;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.services.business.PlayerRoomService;
import com.revature.g2g.services.business.SkillPlayerJTService;
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
@RequestMapping(value="room")
public class RoomController {
	private RoomHandler roomHandler;
	private AuthenticatorHelper authenticatorHelper;
	private RoomHelper roomHelper;
	private PlayerRoomJTHandler playerRoomJTHandler;
	private PlayerRoomService playerRoomService;
	private LoggerSingleton loggerSingleton;
	private GameHandler gameHandler;
	private GuildHelper guildHelper;
	private SkillPlayerJTService skillPlayerJTService;
	
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
	
	@Autowired
	public void setSkillPlayerJTService(SkillPlayerJTService skillPlayerJTService) {
		this.skillPlayerJTService = skillPlayerJTService;
	}
	
	@GetMapping
	public ResponseEntity<List<RoomDTO>> getRooms(){
		List<Room> rooms = roomHandler.findAll();
		List<RoomDTO> returnThis = new ArrayList<RoomDTO>();
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			for (Room room: rooms) {
				returnThis.add(new RoomDTO(room) );
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnThis);
		}
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<RoomDTO> findById(@PathVariable("id") int id){
		Optional<Room> roomOpt = roomHandler.findById(id);
		if(roomOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new RoomDTO(roomOpt.get()) );
		}else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
	}
	
	@PostMapping("")
	public ResponseEntity<RoomDTO> makeNewRoom(@RequestBody RoomDTO roomDTO){
		if(roomDTO == null || roomDTO.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(new PlayerDTO(roomDTO.getSender()) );
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Room room = roomHelper.clean(new Room(roomDTO.getRoom()) );
		room.setCurrentPlayers(0);
		roomHandler.save(room);
		return ResponseEntity.status(HttpStatus.CREATED).body(new RoomDTO(room) );
	}
	
	@PatchMapping("")
	public ResponseEntity<List<RoomDTO>> updateRoom(@RequestBody RoomDTO dto){
		if(dto == null || dto.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(new PlayerDTO(dto.getSender()) );
		if(player == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		if(!(player.getPlayerRole().equals(PlayerRole.ADMIN) || player.getPlayerRole().equals(PlayerRole.MODERATOR))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Room> roomOpt = roomHandler.findById(dto.getRoom().getRoomId());
		if(!roomOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		Room room = roomOpt.get();
		roomHelper.ammend(room, new Room(dto.getRoom()) );
		roomHandler.save(new Room(dto.getRoom()) );
		List<Room> rooms = roomHandler.findAll();
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			List<RoomDTO> returnThis = new ArrayList<RoomDTO>();
			for (Room roomElement : rooms) {
				returnThis.add(new RoomDTO(roomElement) );
			}
			return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(returnThis);
		}
	}
	
	@PostMapping("/player")
	public ResponseEntity<DiscordInviteDTO> playerJoinRoom(@RequestBody PlayerRoomDTO dto){
		if(dto == null || dto.getRoom() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		Player player = authenticatorHelper.getPlayer(dto.getSender() );
		if(player == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Room> roomOpt = roomHandler.findById(dto.getRoom().getRoomId());
		if(!roomOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		Room room = roomOpt.get();
		PlayerRoomJT alreadyInRoom = playerRoomJTHandler.findByPlayerAndRoom(player, room);
		Invite invite = roomHelper.getInvite(room,player);
		if(alreadyInRoom == null) {
			room.setCurrentPlayers(room.getCurrentPlayers() + 1);
			roomHandler.save(room);
			skillPlayerJTService.checkThenAddGamesSkills(player, room.getGame());
		}
		DiscordInviteDTO discordInviteDTO = new DiscordInviteDTO();
		discordInviteDTO.setChannelId(room.getDiscordVoiceChannelId());
		discordInviteDTO.setChannelName(room.getName());
		Guild guild = guildHelper.getGuild();
		discordInviteDTO.setGuildId(guild.getIdLong());
		discordInviteDTO.setGuildName(guild.getName());
		discordInviteDTO.setInviteCode(invite.getCode());
		discordInviteDTO.setUrlWeb(invite.getUrl());
		discordInviteDTO.setUrlApp("discord://discordapp.com/invite/" + invite.getCode());
		return ResponseEntity.status(HttpStatus.CREATED).body(discordInviteDTO);
	}
	
	@PostMapping(value="/style/casual")
	public ResponseEntity<List<RoomDTO>> casual(@Valid @RequestBody PlayerDTO dto){
		Player player = authenticatorHelper.getPlayer(dto);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return getRooms(player, RoomPlayStyle.CASUAL);
	}
	
	@PostMapping(value="/style/hybrid")
	public ResponseEntity<List<RoomDTO>> hybrid(@Valid @RequestBody PlayerDTO dto){
		Player player = authenticatorHelper.getPlayer(dto);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return getRooms(player, RoomPlayStyle.HYBRID);
	}
	
	@PostMapping(value="/style/serious")
	public ResponseEntity<List<RoomDTO>> serious(@Valid @RequestBody PlayerDTO dto){
		Player player = authenticatorHelper.getPlayer(dto);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return getRooms(player, RoomPlayStyle.SERIOUS);
	}
	
	@PostMapping(value="/game/casual/{id}")
	public ResponseEntity<List<RoomDTO>> casualName(@Valid @RequestBody PlayerDTO dto, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(dto);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.CASUAL, gameOpt.get());
	}
	
	@PostMapping(value="/game/hybrid/{id}")
	public ResponseEntity<List<RoomDTO>> hybridName(@Valid @RequestBody PlayerDTO dto, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(dto);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.HYBRID, gameOpt.get());
	}
	
	@PostMapping(value="/game/serious/{id}")
	public ResponseEntity<List<RoomDTO>> seriousName(@Valid @RequestBody PlayerDTO dto, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(dto);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, RoomPlayStyle.SERIOUS, gameOpt.get());
	}
	
	@PostMapping(value="/game/id/{id}")
	public ResponseEntity<List<RoomDTO>> name(@RequestBody PlayerDTO dto, @PathVariable("id") int id){
		Player player = authenticatorHelper.getPlayer(dto);
		if(player==null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		Optional<Game> gameOpt = gameHandler.findById(id);
		if(!gameOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return getRooms(player, gameOpt.get());
	}
	
	private ResponseEntity<List<RoomDTO>> getRooms(Player player, RoomPlayStyle style){
		List<Room> rooms = playerRoomService.getQualifiedRooms(player, style);
		String logMessage = "ViewRoomsController1: qualified rooms requested by: " + player.getPlayerId() + " " + player.getPlayerUsername();
		loggerSingleton.getBusinessLog().trace(logMessage);
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			List<RoomDTO> returnThis = new ArrayList<RoomDTO>();
			for (Room room : rooms) {
				returnThis.add(new RoomDTO(room));
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnThis);
		}
	}
	
	private ResponseEntity<List<RoomDTO>> getRooms(Player player, Game game){
		List<Room> rooms = playerRoomService.getQualifiedRooms(player, game);
		List<RoomDTO> returnThis = new ArrayList<RoomDTO>();
		String logMessage = "ViewRoomsController2: qualified rooms requested by: " + player.getPlayerId() + " " + player.getPlayerUsername();
		loggerSingleton.getBusinessLog().trace(logMessage);
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			for (Room room : rooms) {
				returnThis.add(new RoomDTO(room) );
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnThis);
		}
	}
	
	private ResponseEntity<List<RoomDTO>> getRooms(Player player, RoomPlayStyle style, Game game){
		List<Room> rooms = playerRoomService.getQualifiedRooms(player, style, game);
		List<RoomDTO> returnThis = new ArrayList<RoomDTO>();
		String logMessage = "ViewRoomsController3: qualified rooms requested by: " + player.getPlayerId() + " " + player.getPlayerUsername();
		loggerSingleton.getBusinessLog().trace(logMessage);
		if(rooms.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}else {
			for (Room room : rooms) {
				returnThis.add(new RoomDTO(room) );
			}
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(returnThis);
		}
	}
}