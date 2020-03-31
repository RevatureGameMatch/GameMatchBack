package com.revature.g2g.repositories;

import com.revature.g2g.models.SkillPlayerChangeJT;

public interface ISkillPlayerChangeJTDAO {
	//Create
	public void insert(SkillPlayerChangeJT spc);
	//Read
	//TODO will be needed for analysis and re-balancing starting on full release
	//Update
	public void update(SkillPlayerChangeJT spc);
	//Delete
	public void delete(SkillPlayerChangeJT spc);
}
