package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.repositories.IPlayerDAO;
import com.revature.g2g.repositories.PlayerDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerHandler {
	private IPlayerDAO repository;
	public PlayerHandler() {
		super();
//		this.repository = (PlayerDAO) ApplicationContextSingleton.getApplicationContext().getBean("playerDAO");
		this.repository = new PlayerDAO();
	}
	public PlayerHandler(IPlayerDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(Player p) {
		this.repository.insert(p);
	}
	public Player findById(int id) {
		return this.repository.findById(id);
	}
	public Player findByUsername(String username) {
		return this.repository.findByUsername(username);
	}
	public Player findByEmail(String email) {
		return this.repository.findByEmail(email);
	}
	public Set<Player> findAll(){
		return this.repository.findAll();
	}
	public Set<Player> findByRole(PlayerRole role){
		return this.repository.findByRole(role);
	}
	public void update(Player p) {
		this.repository.update(p);
	}
	public void delete(Player p) {
		this.repository.delete(p);
	}
}
