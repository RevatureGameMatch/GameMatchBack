package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;

public interface ISkillPlayerJT {
	//Create
	public boolean insert(SkillPlayerJT sp);
	//Read
	public SkillPlayerJT findById(int id);
	public int findValue(Player player, Skill skill);
	public Set<SkillPlayerJT> findAll();
	public Set<SkillPlayerJT> findBySkill(Skill skill);
	//Update
	public boolean update(SkillPlayerJT sp);
	//Delete
	public boolean delete(SkillPlayerJT sp);
}
