package com.revature.g2g.services.handlers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.repositories.ISkillPlayerChangeJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerChangeJTHandler {
	private ISkillPlayerChangeJTDAO repository;
	@Autowired
	public SkillPlayerChangeJTHandler(ISkillPlayerChangeJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void save(SkillPlayerChangeJT spc) {
		this.repository.save(spc);
	}
	public SkillPlayerChangeJT findBy(Player modifiedBy, Player player, Room room, Skill skill) {
		return this.repository.findBy(modifiedBy, player, room, skill);
	}
	public List<SkillPlayerChangeJT> findBy(Room room, Player modifiedBy){
		return this.repository.findBy(room, modifiedBy);
	}
	public void delete(SkillPlayerChangeJT spc) {
		this.repository.delete(spc);
	}
}
