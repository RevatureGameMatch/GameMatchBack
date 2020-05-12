package com.revature.g2g.services.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public List<SkillGameJT> findJTBySkill(Skill skill){
		return this.repository.findBySkill(skill);
	}
	public List<Game> findBySkill(Skill skill){
		List<SkillGameJT> joinTables = this.findJTBySkill(skill);
		List<Game> games = new ArrayList<>();
		for(SkillGameJT joinTable : joinTables) {
			games.add(joinTable.getGame());
		}
		return games;
	}
	public SkillGameJT findJTTopSkill(Game game) {
		return this.repository.findTopSkill(game);
	}
	public Skill findTopSkill(Game game) {
		return this.findJTTopSkill(game).getSkill();
	}
	public List<SkillGameJT> findJTByGame(Game game){
		return this.repository.findByGame(game);
	}
	public List<Skill> findByGame(Game game){
		List<SkillGameJT> joinTables = this.findJTByGame(game);
		List<Skill> skills = new ArrayList<>();
		for(SkillGameJT joinTable : joinTables) {
			skills.add(joinTable.getSkill());
		}
		return skills;
	}
	public SkillGameJT findBySkillAndGame(Skill skill, Game game) {
		return this.repository.findBySkillAndGame(skill, game);
	}
	public void delete(SkillGameJT sg) {
		this.repository.delete(sg);
	}
}