package com.revature.g2g.services.handlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.repositories.IRoomDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoomHandler {
	private IRoomDAO repository;
	@Autowired
	public RoomHandler(IRoomDAO repository) {
		super();
		this.repository = repository;
	}
	public void save(Room r) {
		this.repository.save(r);
	}
	public Optional<Room> findById(long id) {
		return this.repository.findById(id);
	}
	public List<Room> findAll(){
		return this.repository.findAll();
	}
	public List<Room> findByStatus(RoomStatus status){
		return this.repository.findByStatus(status);
	}
	public List<Room> findByPlayStyle(RoomPlayStyle style){
		return this.repository.findByPlayStyle(style);
	}
	public List<Room> findByStatusAndPlayStyle(RoomStatus status, RoomPlayStyle style){
		return this.repository.findByStatusAndPlayStyle(status, style);
	}
	public List<Room> findByStatusAndGame(RoomStatus status, Game game){
		return this.repository.findByStatusAndGame(status, game);
	}
	public List<Room> findByStatusAndPlayStyleAndGame(RoomStatus status, RoomPlayStyle style, Game game){
		return this.repository.findByStatusAndPlayStyleAndGame(status, style, game);
	}
	public Room findByDiscordVoiceChannelId(Long discordVoiceId) {
		return this.repository.findByDiscordVoiceChannelId(discordVoiceId);
	}
	public void delete(Room room) {
		this.repository.delete(room);
	}
}