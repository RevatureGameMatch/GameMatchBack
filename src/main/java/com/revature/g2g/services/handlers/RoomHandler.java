package com.revature.g2g.services.handlers;

import java.util.Set;

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
	public RoomHandler() {
		super();
//		this.repository = (RoomDAO) ApplicationContextSingleton.getApplicationContext().getBean("roomDAO");
//		this.repository = new RoomDAO();
	}
	@Autowired
	public RoomHandler(IRoomDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(Room r) {
		this.repository.insert(r);
	}
	public Room findById(int id) {
		return this.repository.findById(id);
	}
	public Set<Room> findAll(){
		return this.repository.findAll();
	}
	public Set<Room> findByStatus(RoomStatus status){
		return this.repository.findByStatus(status);
	}
	public Set<Room> findByPlayStyle(RoomPlayStyle style){
		return this.repository.findByPlayStyle(style);
	}
	public Set<Room> findStatusPlayStyle(RoomStatus status, RoomPlayStyle style){
		return this.repository.findStatusPlayStyle(status, style);
	}
	public Set<Room> findByStatusGame(RoomStatus status, Game game){
		return this.repository.findByStatusGame(status, game);
	}
	public Set<Room> findByStatusPlayStyleGame(RoomStatus status, RoomPlayStyle style, Game game){
		return this.repository.findByStatusPlayStyleGame(status, style, game);
	}
	public Room findRoomByDiscordVoice(Long discordVoiceId) {
		return this.repository.findRoomByDiscordVoice(discordVoiceId);
	}
//	public Set<Room> findBySkill(RoomStatus status, SkillPlayerJT[] skills){
//		return this.repository.findBySkill(status, skills);
//	}
	public void update(Room r) {
		this.repository.update(r);
	}
	public void delete(Room r) {
		this.repository.delete(r);
	}
}