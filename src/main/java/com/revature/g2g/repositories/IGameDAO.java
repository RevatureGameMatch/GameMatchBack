package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;

public interface IGameDAO {
	//Create
	public void insert(Game g);
	//Read
	public Game findById(int id);
	public Game findByName(String name);
	public Set<Game> findAll();
	public Set<Game> findBySkill(Skill skill);
	//Update
	public void update(Game g);
	//Delete
	public void delete(Game g);
}
