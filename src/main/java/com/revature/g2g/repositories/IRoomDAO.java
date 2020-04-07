package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;

public interface IRoomDAO {
	//Create
	public void insert(Room r);
	//Read
	public Room findById(int id);
	public Set<Room> findAll();
	public Set<Room> findByStatus(RoomStatus status);
	public Set<Room> findByPlayStyle(RoomPlayStyle style);
	public Set<Room> findStatusPlayStyle(RoomStatus status, RoomPlayStyle style);
	public Room findRoomByDiscordVoice(Long discordVoiceId);
	//Find rooms someone can log into
	//public Set<Room> findBySkill(RoomStatus status, SkillPlayerJT[] skills);
	//Update
	public void update(Room r);
	//Delete
	public void delete(Room r);
}
