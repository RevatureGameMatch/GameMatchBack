package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.repositories.ISkillPlayerJTDAO;
import com.revature.g2g.repositories.SkillPlayerJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerJTHandler {
	private ISkillPlayerJTDAO repository;
	public SkillPlayerJTHandler() {
		super();
		this.repository = new SkillPlayerJTDAO();
	}
	public SkillPlayerJTHandler(ISkillPlayerJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(SkillPlayerJT sp) {
		this.repository.insert(sp);
	}
	public SkillPlayerJT findById(int id) {
		return this.repository.findById(id);
	}
	public double findValue(Player player, Skill skill) {
		return this.repository.findValue(player, skill);
	}
	public Set<SkillPlayerJT> findAll(){
		return this.repository.findAll();
	}
	public Set<SkillPlayerJT> findBySkill(Skill skill){
		return this.repository.findBySkill(skill);
	}
	public SkillPlayerJT findBySkillPlayer(Skill skill, Player player) {
		return this.repository.findBySkillPlayer(skill, player);
	}
	public void update(SkillPlayerJT sp) {
		this.repository.update(sp);
	}
	public void delete(SkillPlayerJT sp) {
		this.repository.delete(sp);
	}
}
