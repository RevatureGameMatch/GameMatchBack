package com.revature.g2g.data.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Player;
import com.revature.g2g.models.PlayerRole;
import com.revature.g2g.services.handlers.PlayerHandler;
import com.revature.g2g.services.helpers.PasswordHelper;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PlayerGenerator implements DataGenerator {
	@Autowired
	private PasswordHelper passwordHelper;
	@Autowired
	private PlayerHandler playerHandler;
	@Override
	public void generate() {
		make("BobTheGreat",PlayerRole.PLAYER);
		make("JillTheStrong",PlayerRole.PLAYER);
		make("RodrickTheValiant",PlayerRole.PLAYER);
		make("StephanieTheVictorious",PlayerRole.PLAYER);
		make("BillTheDecimator",PlayerRole.PLAYER);
		make("RyanTheFoolish",PlayerRole.PLAYER);
		make("KaylaTheWise",PlayerRole.PLAYER);
		make("KyleTheMusical",PlayerRole.PLAYER);
		make("NancyThePrepared",PlayerRole.PLAYER);
		make("ValdrithTheMerciful",PlayerRole.PLAYER);
		make("Player",PlayerRole.PLAYER);
		make("RadrinTheKind",PlayerRole.MODERATOR);
		make("MalinTheBoastful",PlayerRole.MODERATOR);
		make("ThranTheMeak",PlayerRole.MODERATOR);
		make("Moderator",PlayerRole.MODERATOR);
		make("Philip",PlayerRole.ADMIN);
		make("Nancy",PlayerRole.ADMIN);
		make("Kayla",PlayerRole.ADMIN);
		make("Admin",PlayerRole.ADMIN);
	}
	private void make(String username, PlayerRole role) {
		Player player = new Player();
		player.setPlayerUsername(username);
		if(playerHandler.findByPlayerUsername(player.getPlayerUsername()) == null) {
			player.setPlayerPassword(passwordHelper.encryptPassword("password"));
			player.setPlayerEmail(username + "@gmail.com");
			player.setPlayerRole(role);
			playerHandler.save(player);
		}
	}
}
