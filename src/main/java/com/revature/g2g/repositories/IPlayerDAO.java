package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;

public interface IPlayerDAO {
	//Create
	public boolean insert(Player p);
	//Read
	public Player findById(int id);
	public Player findByUsername(String username);
	public Player findByEmail(String email);
	public Set<Player> findAll();
	public Set<Player> findByRole(PlayerRole role);
	//Update
	public boolean update(Player p);
	//Delete
	public boolean delete(Player p);
}
