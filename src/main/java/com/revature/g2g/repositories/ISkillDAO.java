package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Skill;

public interface ISkillDAO {
	//Create
	public void insert(Skill s);
	//Read
	public Skill findById(int id);
	public Skill findByName(String name);
	public Set<Skill> findAll();
	public Set<Skill> findByParent(Skill skill);
	//Update
	public void update(Skill s);
	//Delete
	public void delete(Skill s);
}
