package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.repositories.ISkillGameJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillGameJTHandler {
	private ISkillGameJTDAO repository;
	public SkillGameJTHandler() {
		super();
	}
	@Autowired
	public SkillGameJTHandler(ISkillGameJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(SkillGameJT sg) {
		this.repository.insert(sg);
	}
	public SkillGameJT findById(int id) {
		return this.repository.findById(id);
	}
	public Set<SkillGameJT> findAll(){
		return this.repository.findAll();
	}
	public Set<Game> findBySkill(Skill skill){
		return this.repository.findBySkill(skill);
	}
	public Skill findTopSkill(Game game) {
		return this.repository.findTopSkill(game);
	}
	public Set<Skill> findByGame(Game game){
		return this.repository.findByGame(game);
	}
	public SkillGameJT findBySkillGame(Skill skill, Game game) {
		return this.repository.findBySkillGame(skill, game);
	}
	public void update(SkillGameJT sg) {
		this.repository.update(sg);
	}
	public void delete(SkillGameJT sg) {
		this.repository.delete(sg);
	}
}