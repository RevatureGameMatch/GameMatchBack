package com.revature.g2g.services.helpers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerDTO;
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
	 * @param dto
	 * @return Player
	 */
	public Player getPlayer(@Valid PlayerDTO dto) {
		if(dto == null)return null;
		Optional<Player> playerOpt = playerHandler.findById(dto.getPlayerId());
		if(!playerOpt.isPresent())return null;
		Player player = playerOpt.get();
		if(player == null || player.getPlayerEmail() == null || player.getPlayerUsername() == null)return null;
		if(!player.getPlayerEmail().equals(dto.getPlayerEmail()))return null;
		if(!player.getPlayerUsername().equals(dto.getPlayerUsername()))return null;
		return player;
	}
}