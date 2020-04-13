package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;

public interface ISkillRoomJTDAO {
	//Create
	public void insert(SkillRoomJT sr);
	//Read
	public SkillRoomJT findById(int id);
//	public SkillRoomJT findBySkillName(String name);
	public Set<SkillRoomJT> findAll();
	public Set<SkillRoomJT> findBySkill(Skill skill);
	public Set<SkillRoomJT> findByRoom(Room room);
	//Update
	public void update(SkillRoomJT sr);
	//Delete
	public void delete(SkillRoomJT sr);
	Set<Skill> findSkillsByRoom(Room room);
}
