package com.revature.g2g.services.handlers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.DiscordInvite;
import com.revature.g2g.models.DiscordInviteStatus;
import com.revature.g2g.models.Player;
import com.revature.g2g.repositories.IDiscordInviteDAO;

@Service
public class DiscordInviteHandler{
	private IDiscordInviteDAO repository;
	@Autowired
	public DiscordInviteHandler(IDiscordInviteDAO repository) {
		super();
		this.repository = repository;
	}
	public void insert(DiscordInvite discordInvite) {
		this.repository.insert(discordInvite);
	}
	public DiscordInvite findById(int id) {
		return this.repository.findById(id);
	}
	public DiscordInvite findByDiscordCode(String discordCode) {
		return this.repository.findByDiscordCode(discordCode);
	}
	public Set<DiscordInvite> findAll() {
		return this.repository.findAll();
	}
	public Set<DiscordInvite> findByStatus(DiscordInviteStatus status) {
		return this.findByStatus(status);
	}
	public Set<DiscordInvite> findByPlayer(Player player) {
		return this.findByPlayer(player);
	}
	public void update(DiscordInvite discordInvite) {
		this.repository.update(discordInvite);
	}
	public void delete(DiscordInvite discordInvite) {
		this.repository.delete(discordInvite);
	}
}
