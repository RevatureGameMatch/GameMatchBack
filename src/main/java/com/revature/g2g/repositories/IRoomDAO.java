package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;

public interface IRoomDAO extends JpaRepository<Room, Long>{
	//Read
	public List<Room> findByStatus(RoomStatus status);
	public List<Room> findByPlayStyle(RoomPlayStyle style);
	public List<Room> findByStatusAndPlayStyle(RoomStatus status, RoomPlayStyle style);
	public List<Room> findByStatusAndGame(RoomStatus status, Game game);
	public List<Room> findByStatusAndPlayStyleAndGame(RoomStatus status, RoomPlayStyle style, Game game);
	public Room findByDiscordVoiceChannelId(Long discordVoiceId);
}
