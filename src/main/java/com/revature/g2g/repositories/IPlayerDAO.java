package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;

public interface IPlayerDAO extends JpaRepository<Player, Long>{
	//Read
	public Player findByPlayerUsername(String username);
	public Player findByPlayerEmail(String email);
	public List<Player> findByPlayerRole(PlayerRole role);
}
