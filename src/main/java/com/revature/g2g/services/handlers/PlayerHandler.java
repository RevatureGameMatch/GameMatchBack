package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.repositories.IPlayerDAO;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerHandler {
	@Autowired
	private IPlayerDAO repository;
	public void insert(Player p) {
		repository.insert(p);
	}
	public Player findById(int id) {
		return repository.findById(id);
	}
	public Player findByUsername(String username) {
		return repository.findByUsername(username);
	}
	public Player findByEmail(String email) {
		return repository.findByEmail(email);
	}
	public Set<Player> findAll(){
		return repository.findAll();
	}
	public Set<Player> findByRole(PlayerRole role){
		return repository.findByRole(role);
	}
	public void update(Player p) {
		repository.update(p);
	}
	public void delete(Player p) {
		repository.delete(p);
	}
}
