package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Skill;
import com.revature.g2g.repositories.ISkillDAO;
import com.revature.g2g.repositories.SkillDAO;
import com.revature.g2g.services.spring.ApplicationContextSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillHandler {
	private ISkillDAO repository;
	public SkillHandler() {
		super();
		this.repository = (SkillDAO) ApplicationContextSingleton.getApplicationContext().getBean("skillDAO");
	}
	public SkillHandler(ISkillDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(Skill s) {
		this.repository.insert(s);
	}
	public Skill findById(int id) {
		return this.repository.findById(id);
	}
	public Skill findByName(String name) {
		return this.repository.findByName(name);
	}
	public Set<Skill> findAll(){
		return this.repository.findAll();
	}
	public Set<Skill> findByParent(Skill skill){
		return this.repository.findByParent(skill);
	}
	public void update(Skill s) {
		this.repository.update(s);
	}
	public void delete(Skill s) {
		this.repository.delete(s);
	}
}