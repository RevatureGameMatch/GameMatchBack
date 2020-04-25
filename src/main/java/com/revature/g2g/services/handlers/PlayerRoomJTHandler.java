package com.revature.g2g.services.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;
import com.revature.g2g.repositories.IPlayerRoomJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerRoomJTHandler {
	private IPlayerRoomJTDAO repository;
	@Autowired
	public PlayerRoomJTHandler(IPlayerRoomJTDAO repository) {
		this.repository = repository;
	}
	public void save(PlayerRoomJT pr) {
		this.repository.save(pr);
	}
	public Optional<PlayerRoomJT> findById(long id) {
		return this.repository.findById(id);
	}
	public int countCurrentPlayers() {
		return this.repository.countCurrentPlayers();
	}
	public int countCurrentPlayers(Room room) {
		return this.repository.countCurrentPlayers(room);
	}
	public PlayerRoomJT findByPlayerAndRoom(Player player, Room room) {
		return this.repository.findByPlayerAndRoom(player, room);
	}
	public List<PlayerRoomJT> findByPlayer(Player player){
		return this.repository.findByPlayer(player);
	}
	public List<PlayerRoomJT> findAll(){
		return this.repository.findAll();
	}
	public List<PlayerRoomJT> findByRoom(Room room){
		return this.repository.findByRoom(room);
	}
	public List<Player> findPlayers(Room room){
		List<PlayerRoomJT> joinTables = this.findByRoom(room);
		List<Player> players = new ArrayList<>();
		for(PlayerRoomJT joinTable : joinTables) {
			players.add(joinTable.getPlayer());
		}
		return players;
	}
	public List<Room> findRooms(Player player){
		List<PlayerRoomJT> joinTables = this.findByPlayer(player);
		List<Room> rooms = new ArrayList<>();
		for(PlayerRoomJT joinTable : joinTables) {
			rooms.add(joinTable.getRoom());
		}
		return rooms;
	}
	public List<PlayerRoomJT> findSurveyRoomsJT(Player player){
		return this.repository.findSurveyRooms(player);
	}
	public List<Room> findSurveyRooms(Player player){
		List<PlayerRoomJT> joinTables = this.findSurveyRoomsJT(player);
		List<Room> rooms = new ArrayList<>();
		for(PlayerRoomJT joinTable : joinTables) {
			rooms.add(joinTable.getRoom());
		}
		return rooms;
	}
	public void delete(PlayerRoomJT pr) {
		this.repository.delete(pr);
	}
}
