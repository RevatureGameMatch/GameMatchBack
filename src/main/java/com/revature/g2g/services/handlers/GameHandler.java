package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.repositories.GameDAO;
import com.revature.g2g.repositories.IGameDAO;
import com.revature.g2g.services.spring.ApplicationContextSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GameHandler {
	private IGameDAO repository;
	public GameHandler() {
		super();
		this.repository = (GameDAO) ApplicationContextSingleton.getApplicationContext().getBean("gameDAO");
	}
	public GameHandler(IGameDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(Game g) {
		this.repository.insert(g);
	}
	public Game findById(int id) {
		return this.repository.findById(id);
	}
	public Game findByName(String name) {
		return this.repository.findByName(name);
	}
	public Set<Game> findAll(){
		return this.repository.findAll();
	}
	public void update(Game g) {
		this.repository.update(g);
	}
	public void delete(Game g) {
		this.repository.delete(g);
	}
}
