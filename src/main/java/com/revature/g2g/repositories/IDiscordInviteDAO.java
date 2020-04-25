package com.revature.g2g.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.g2g.models.DiscordInvite;
import com.revature.g2g.models.DiscordInviteStatus;
import com.revature.g2g.models.Player;

public interface IDiscordInviteDAO extends JpaRepository<DiscordInvite, Integer>{
	//Read
	public DiscordInvite findByDiscordCode(String discordCode);
	public List<DiscordInvite> findByStatus(DiscordInviteStatus status);
	public List<DiscordInvite> findByPlayer(Player player);
}
