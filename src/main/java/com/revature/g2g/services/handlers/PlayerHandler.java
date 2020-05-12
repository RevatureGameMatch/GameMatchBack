package com.revature.g2g.services.handlers;

import java.util.Optional;
import java.util.List;

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
	public void save(Player p) {
		repository.save(p);
	}
	public Optional<Player> findById(long id) {
		return repository.findById(id);
	}
	public Player findByPlayerUsername(String username) {
		return repository.findByPlayerUsername(username);
	}
	public Player findByEmail(String email) {
		return repository.findByPlayerEmail(email);
	}
	public List<Player> findAll(){
		return repository.findAll();
	}
	public List<Player> findByRole(PlayerRole role){
		return repository.findByPlayerRole(role);
	}
	public void delete(Player p) {
		repository.delete(p);
	}
}
