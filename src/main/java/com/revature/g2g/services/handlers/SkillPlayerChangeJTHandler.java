package com.revature.g2g.services.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.SkillPlayerChangeJT;
import com.revature.g2g.repositories.ISkillPlayerChangeJTDAO;
import com.revature.g2g.repositories.SkillPlayerChangeJTDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillPlayerChangeJTHandler {
	private ISkillPlayerChangeJTDAO repository;
	public SkillPlayerChangeJTHandler() {
		super();
		this.repository = new SkillPlayerChangeJTDAO();
	}
	@Autowired
	public SkillPlayerChangeJTHandler(ISkillPlayerChangeJTDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(SkillPlayerChangeJT spc) {
		this.repository.insert(spc);
	}
	public void update(SkillPlayerChangeJT spc) {
		this.repository.update(spc);
	}
	public void delete(SkillPlayerChangeJT spc) {
		this.repository.delete(spc);
	}
}
