package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;

public interface ISkillGameJTDAO {
	//Create
	public void insert(SkillGameJT sg);
	//Read
	public SkillGameJT findById(int id);
	public Set<SkillGameJT> findAll();
	public Set<Game> findBySkill(Skill skill);
	public Skill findTopSkill(Game game);
	public Set<Skill> findByGame(Game game);
	//Update
	public void update(SkillGameJT sg);
	//Delete
	public void delete(SkillGameJT sg);
	SkillGameJT findBySkillGame(Skill skill, Game game);
}
