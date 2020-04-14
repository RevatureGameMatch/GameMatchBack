package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;

public interface ISkillPlayerChangeJTDAO {
	//Create
	public void insert(SkillPlayerChangeJT spc);
	//Read
	//TODO will be needed for analysis and re-balancing starting on full release
	public Set<SkillPlayerChangeJT> findBy(Room room, Player player);
	public SkillPlayerChangeJT findBy(Player modifiedBy, Player player, Room room, Skill skill);
	//Update
	public void update(SkillPlayerChangeJT spc);
	//Delete
	public void delete(SkillPlayerChangeJT spc);
}
