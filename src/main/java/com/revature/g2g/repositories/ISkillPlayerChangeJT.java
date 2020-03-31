package com.revature.g2g.repositories;

import com.revature.g2g.models.SkillPlayerChangeJT;

public interface ISkillPlayerChangeJT {
	//Create
	public boolean insert(SkillPlayerChangeJT spc);
	//Read
	//TODO will be needed for analysis and re-balancing starting on full release
	//Update
	public boolean update(SkillPlayerChangeJT spc);
	//Delete
	public boolean delete(SkillPlayerChangeJT spc);
}
