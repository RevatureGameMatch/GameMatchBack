package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRoomJT;
import com.revature.g2g.models.Room;

public interface IPlayerRoomJTDAO extends JpaRepository<PlayerRoomJT, Long>{
	//Read
	@Query(value = "SELECT count(jt) from PlayerRoomJT jt WHERE jt.left is null")
	public int countCurrentPlayers();
	@Query(value = "SELECT count(jt) from PlayerRoomJT jt WHERE jt.left is null AND jt.room = ?1")
	public int countCurrentPlayers(Room room);
	public PlayerRoomJT findByPlayerAndRoom(Player player, Room room);
	@Query(value = "SELECT jt from PlayerRoomJT jt WHERE jt.left is null")
	public List<PlayerRoomJT> findCurrentPlayers();
	@Query(value = "SELECT jt from PlayerRoomJT jt WHERE jt.left is null AND jt.room = ?1")
	public List<PlayerRoomJT> findCurrentPlayersByRoom(Room room);
	public List<PlayerRoomJT> findByPlayer(Player player);
	public List<PlayerRoomJT> findByRoom(Room room);
	//HQL timestamp is in days. 0.007 ~ 10 minutes
	@Query(value = "SELECT jt from PlayerRoomJT jt JOIN jt.room rm WHERE jt.player = ?1 AND rm.created < (current_timestamp - 0.007) AND (rm.closed is null OR rm.closed > (current_timestamp - 1))")
	public List<PlayerRoomJT> findSurveyRooms(Player player);
}
