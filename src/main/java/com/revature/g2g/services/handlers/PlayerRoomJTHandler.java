package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.repositories.IPlayerRoomJTDAO;
import com.revature.g2g.repositories.PlayerRoomJTDAO;
import com.revature.g2g.services.spring.ApplicationContextSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomJTHandler {
	private IPlayerRoomJTDAO repository;
	public PlayerRoomJTHandler() {
		super();
		this.repository = (PlayerRoomJTDAO) ApplicationContextSingleton.getApplicationContext().getBean("playerRoomJTHanlderDAO");
	}
	public PlayerRoomJTHandler(IPlayerRoomJTDAO repository) {
		this.repository = repository;
	}
	public void insert(PlayerRoomJT pr) {
		this.repository.insert(pr);
	}
	public PlayerRoomJT findById(int id) {
		return this.repository.findById(id);
	}
	public int countCurrentPlayers() {
		return this.repository.countCurrentPlayers();
	}
	public int countCurrentPlayers(Room room) {
		return this.repository.countCurrentPlayers(room);
	}
	public Set<PlayerRoomJT> findAll(){
		return this.repository.findAll();
	}
	public Set<Player> findPlayers(Room room){
		return this.repository.findPlayers(room);
	}
	public Set<Room> findRooms(Player player){
		return this.repository.findRooms(player);
	}
	public void update(PlayerRoomJT pr) {
		this.repository.update(pr);
	}
	public void delete(PlayerRoomJT pr) {
		this.repository.delete(pr);
	}
}
