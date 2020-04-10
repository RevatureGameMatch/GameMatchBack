package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;

public interface IPlayerRoomJTDAO {
	//Create
	public void insert(PlayerRoomJT pr);
	//Read
	public PlayerRoomJT findById(int id);
	public int countCurrentPlayers();
	public int countCurrentPlayers(Room room);
	public PlayerRoomJT findByPlayerRoom(Player player, Room room);
	public Set<PlayerRoomJT> findAll();
	public Set<Player> findPlayers(Room room);
	public Set<Room> findRooms(Player player);
	//Update
	public void update(PlayerRoomJT pr);
	//Delete
	public void delete(PlayerRoomJT pr);
}
