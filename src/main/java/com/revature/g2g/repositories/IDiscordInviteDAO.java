package com.revature.g2g.repositories;

import java.util.Set;

import com.revature.g2g.models.DiscordInvite;
import com.revature.g2g.models.DiscordInviteStatus;
import com.revature.g2g.models.Player;

public interface IDiscordInviteDAO {
	//Create
	public void insert(DiscordInvite discordInvite);
	//Read
	public DiscordInvite findById(int id);
	public DiscordInvite findByDiscordCode(String discordCode);
	public Set<DiscordInvite> findAll();
	public Set<DiscordInvite> findByStatus(DiscordInviteStatus status);
	public Set<DiscordInvite> findByPlayer(Player player);
	//Update
	public void update(DiscordInvite discordInvite);
	//Delete
	public void delete(DiscordInvite discordInvite);
}
