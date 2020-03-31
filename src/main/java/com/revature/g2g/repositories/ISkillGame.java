package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;

public interface ISkillGame {
	//Create
	public boolean insert(SkillGameJT sg);
	//Read
	public SkillGameJT findById(int id);
	public Set<SkillGameJT> findAll();
	public Set<Game> findBySkill(Skill skill);
	public Skill findTopSkill(Game game);
	public Set<Skill> findByGame(Game game);
	//Update
	public boolean update(SkillGameJT sg);
	//Delete
	public boolean delete(SkillGameJT sg);
}
