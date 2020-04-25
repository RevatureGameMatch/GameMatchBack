package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Skill;

public interface ISkillDAO extends JpaRepository<Skill, Long>{
	//Read
	public Skill findByName(String name);
	public List<Skill> findByParentSkill(Skill skill);
}
