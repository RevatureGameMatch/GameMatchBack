package com.revature.g2g.services.handlers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.repositories.IGameDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GameHandler {
	private IGameDAO repository;
	public GameHandler() {
		super();
	}
	@Autowired
	public GameHandler(IGameDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(Game g) {
		this.repository.save(g);
	}
	public Optional<Game> findById(long id) {
		return this.repository.findById(id);
	}
	public Game findByName(String name) {
		return this.repository.findByName(name);
	}
	public List<Game> findAll(){
		return this.repository.findAll();
	}
	public void update(Game g) {
		this.repository.update(g);
	}
	public void delete(Game g) {
		this.repository.delete(g);
	}
}
