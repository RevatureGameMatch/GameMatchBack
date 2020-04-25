package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;

public interface ISkillPlayerChangeJTDAO extends JpaRepository<SkillPlayerChangeJT, Long>{
	//Read
	//TODO will be needed for analysis and re-balancing starting on full release
	public List<SkillPlayerChangeJT> findBy(Room room, Player player);
	public SkillPlayerChangeJT findBy(Player modifiedBy, Player player, Room room, Skill skill);
}
