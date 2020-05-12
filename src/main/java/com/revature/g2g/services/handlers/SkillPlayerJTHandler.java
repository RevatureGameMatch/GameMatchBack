package com.revature.g2g.services.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillPlayerJT;
import com.revature.g2g.repositories.ISkillPlayerJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerJTHandler {
	private ISkillPlayerJTDAO repository;
	@Autowired
	public SkillPlayerJTHandler(ISkillPlayerJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void save(SkillPlayerJT skillPlayerJT) {
		this.repository.save(skillPlayerJT);
	}
	public Optional<SkillPlayerJT> findById(long id) {
		return this.repository.findById(id);
	}
	public double findValue(Player player, Skill skill) {
		SkillPlayerJT joinTable = this.findBySkillPlayer(skill, player);
		return joinTable.getValue();
	}
	public List<SkillPlayerJT> findAll(){
		return this.repository.findAll();
	}
	public List<SkillPlayerJT> findBySkill(Skill skill){
		return this.repository.findBySkill(skill);
	}
	public List<SkillPlayerJT> findByPlayer(Player player){
		return this.repository.findByPlayer(player);
	}
	public List<Skill> findPlayerSkills(Player player){
		List<SkillPlayerJT> joinTables = this.repository.findByPlayer(player);
		List<Skill> skills = new ArrayList<>();
		for(SkillPlayerJT joinTable : joinTables) {
			skills.add(joinTable.getSkill());
		}
		return skills;
	}
	public SkillPlayerJT findBySkillPlayer(Skill skill, Player player) {
		return this.repository.findBySkillAndPlayer(skill, player);
	}
	public void delete(SkillPlayerJT sp) {
		this.repository.delete(sp);
	}
}
