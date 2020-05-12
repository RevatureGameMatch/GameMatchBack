package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;

public interface ISkillGameJTDAO extends JpaRepository<SkillGameJT, Long>{
	//Read
	public List<SkillGameJT> findBySkill(Skill skill);
	@Query(value = "SELECT jt from SkillGameJT jt WHERE jt.game = ?1 ORDER BY jt.relevance DESC")
	public SkillGameJT findTopSkill(Game game);
	public List<SkillGameJT> findByGame(Game game);
	public SkillGameJT findBySkillAndGame(Skill skill, Game game);
}
