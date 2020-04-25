package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillRoomJT;

public interface ISkillRoomJTDAO extends JpaRepository<SkillRoomJT, Long>{
	//Read
	public List<SkillRoomJT> findBySkill(Skill skill);
	public List<SkillRoomJT> findByRoom(Room room);
	public List<Skill> findSkillsByRoom(Room room);
}
