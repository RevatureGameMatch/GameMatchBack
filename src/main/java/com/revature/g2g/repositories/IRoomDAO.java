package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.models.SkillPlayerJT;

public interface IRoomDAO {
	//Create
	public void insert(Room r);
	//Read
	public Room findById(int id);
	public Set<Room> findAll();
	public Set<Room> findByStatus(RoomStatus status);
	//Find rooms someone can log into
	//public Set<Room> findBySkill(RoomStatus status, SkillPlayerJT[] skills);
	//Update
	public void update(Room r);
	//Delete
	public void delete(Room r);
}
