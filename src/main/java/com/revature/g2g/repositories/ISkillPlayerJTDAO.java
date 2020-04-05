package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;

public interface ISkillPlayerJTDAO {
	//Create
	public void insert(SkillPlayerJT sp);
	//Read
	public SkillPlayerJT findById(int id);
	public double findValue(Player player, Skill skill);
	public Set<SkillPlayerJT> findAll();
	public Set<SkillPlayerJT> findBySkill(Skill skill);
	//Update
	public void update(SkillPlayerJT sp);
	//Delete
	public void delete(SkillPlayerJT sp);
	Set<Skill> findPlayerSkills(Player player);
}
