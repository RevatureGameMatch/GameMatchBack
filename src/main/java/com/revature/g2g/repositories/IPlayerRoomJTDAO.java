package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;

public interface IPlayerRoomJTDAO extends JpaRepository<PlayerRoomJT, Long>{
	//Read
	public int countCurrentPlayers();
	public int countCurrentPlayers(Room room);
	public PlayerRoomJT findByPlayerAndRoom(Player player, Room room);
	public List<PlayerRoomJT> findByPlayer(Player player);
	public List<PlayerRoomJT> findByRoom(Room room);
	public List<Player> findPlayers(Room room);
	public List<Room> findRooms(Player player);
	public List<Room> findSurveyRooms(Player player);
}
