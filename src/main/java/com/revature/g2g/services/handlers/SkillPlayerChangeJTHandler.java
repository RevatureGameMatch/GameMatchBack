package com.revature.g2g.services.handlers;

import java.util.Set;

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
	public SkillPlayerChangeJTHandler() {
		super();
	}
	@Autowired
	public SkillPlayerChangeJTHandler(ISkillPlayerChangeJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(SkillPlayerChangeJT spc) {
		this.repository.insert(spc);
	}
	public Set<SkillPlayerChangeJT> findBy(Room room, Player modifiedBy){
		return this.repository.findBy(room, modifiedBy);
	}
	public void update(SkillPlayerChangeJT spc) {
		this.repository.update(spc);
	}
	public void delete(SkillPlayerChangeJT spc) {
		this.repository.delete(spc);
	}
	public SkillPlayerChangeJT findBy(Player modifiedBy, Player player, Room room, Skill skill) {
		return this.repository.findBy(modifiedBy, player, room, skill);
	}
}
