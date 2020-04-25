package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;

public interface ISkillGameJTDAO extends JpaRepository<SkillGameJT, Long>{
	//Read
	public List<Game> findBySkill(Skill skill);
	public Skill findTopSkill(Game game);
	public List<Skill> findByGame(Game game);
	public SkillGameJT findBySkillGame(Skill skill, Game game);
}
