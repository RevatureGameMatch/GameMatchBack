package com.revature.g2g.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.Game;

public interface IGameDAO extends JpaRepository<Game, Long>{
	public Game findByName(String name);
}
