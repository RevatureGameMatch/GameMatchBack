package com.revature.g2g.services.handlers;

import java.util.Optional;
import java.util.List;

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
	@Autowired
	public SkillGameJTHandler(ISkillGameJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void save(SkillGameJT skillGameJT) {
		this.repository.save(skillGameJT);
	}
	public Optional<SkillGameJT> findById(long id) {
		return this.repository.findById(id);
	}
	public List<SkillGameJT> findAll(){
		return this.repository.findAll();
	}
	public List<Game> findBySkill(Skill skill){
		return this.repository.findBySkill(skill);
	}
	public Skill findTopSkill(Game game) {
		return this.repository.findTopSkill(game);
	}
	public List<Skill> findByGame(Game game){
		return this.repository.findByGame(game);
	}
	public SkillGameJT findBySkillGame(Skill skill, Game game) {
		return this.repository.findBySkillGame(skill, game);
	}
	public void delete(SkillGameJT sg) {
		this.repository.delete(sg);
	}
}