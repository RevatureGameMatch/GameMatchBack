package com.revature.g2g.services.handlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Skill;
import com.revature.g2g.repositories.ISkillDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillHandler {
	private ISkillDAO repository;
	@Autowired
	public SkillHandler(ISkillDAO repository) {
		super();
		this.repository = repository;
	}
	public void save(Skill s) {
		this.repository.save(s);
	}
	public Optional<Skill> findById(long id) {
		return this.repository.findById(id);
	}
	public Skill findByName(String name) {
		return this.repository.findByName(name);
	}
	public List<Skill> findAll(){
		return this.repository.findAll();
	}
	public List<Skill> findByParent(Skill skill){
		return this.repository.findByParent(skill);
	}
	public void delete(Skill s) {
		this.repository.delete(s);
	}
}