package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;

public interface ISkillRoomJT {
	//Create
	public boolean insert(SkillRoomJT sr);
	//Read
	public SkillRoomJT findById(int id);
	public SkillRoomJT findByName(String name);
	public Set<SkillRoomJT> findAll();
	public Set<SkillRoomJT> findBySkill(Skill skill);
	public Set<SkillRoomJT> findByRoom(Room room);
	//Update
	public boolean update(SkillRoomJT sr);
	//Delete
	public boolean delete(SkillRoomJT sr);
}
