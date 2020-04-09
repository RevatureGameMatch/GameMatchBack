package com.revature.g2g.services.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.api.templates.PlayerTemplate;
import com.revature.g2g.models.Player;
import com.revature.g2g.services.handlers.PlayerHandler;

@Service
public class AuthenticatorHelper {
	private PlayerHandler playerHandler;
	@Autowired
	public AuthenticatorHelper(PlayerHandler playerHandler) {
		super();
		this.playerHandler = playerHandler;
	}
	/**
	 * Checks to see if a template's id exists and if its email
	 * and username match the existing element
	 * @param template
	 * @return Player
	 */
	public Player getPlayer(PlayerTemplate template) {
		if(template == null)return null;
		Player player = playerHandler.findById(template.getPlayerId());
		if(!player.getPlayerEmail().equals(player.getPlayerEmail()))return null;
		if(!player.getPlayerUsername().equals(player.getPlayerUsername()))return null;
		return player;
	}
}