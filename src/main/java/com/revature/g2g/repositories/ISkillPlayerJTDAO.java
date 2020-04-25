package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;

public interface ISkillPlayerJTDAO extends JpaRepository<SkillPlayerJT, Long>{
	//Read
	public double findValue(Player player, Skill skill);
	public List<SkillPlayerJT> findBySkill(Skill skill);
	public List<SkillPlayerJT> findByPlayer(Player player);
	public List<Skill> findPlayerSkills(Player player);
	public SkillPlayerJT findBySkillPlayer(Skill skill, Player player);
}
