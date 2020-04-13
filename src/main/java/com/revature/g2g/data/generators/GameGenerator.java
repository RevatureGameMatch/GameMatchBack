package com.revature.g2g.data.generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Game;
import com.revature.g2g.services.handlers.GameHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class GameGenerator implements DataGenerator {
	private GameHandler gameHandler;
	@Autowired
	public GameGenerator(GameHandler gameHandler) {
		super();
		this.gameHandler = gameHandler;
	}
	@Override
	public void generate() {
		make("DnD5e", "Classic TTRPG to go adventuring with friends", "https://dnd.wizards.com/",0);
		make("Halo", "Hunt your enemies", "https://www.halowaypoint.com/en-us",0);
		make("Call of Duty", "Hunt your enemies", "https://www.callofduty.com/",0);
		make("Minecraft", "Build together, stay together.", "https://www.minecraft.net/en-us/",0);
		make("Fortnite", "Massive Battle Royal", "https://www.epicgames.com/fortnite/en-US/home",0);
		make("League of Legends", "Take down the Nexus", "https://play.na.leagueoflegends.com/en_US",0);
		make("Maple Story", "Adventure Together", "http://maplestory.com/en",0);
		make("Smite", "Smite your enemies", "https://www.smitegame.com/play-for-free/",0);
		make("Don't Starve Together", "Work together to survive!", "https://www.klei.com/games/dont-starve-together",0);
		make("Overcooked", "Can your culinary skills satisfy the customers?", "http://www.ghosttowngames.com/overcooked/",0);
		make("Keep Talking and Nobody Explodes", "One player has a bomb, the rest have the instructions. Can you defuse it in time?", "https://keeptalkinggame.com/",0);
		make("Sea of Thieves", "Sail together, plunder together", "https://www.seaofthieves.com/",0);
		make("Other", "Those games that don't fit into an official game", "",0);
	}
	private void make(String name, String description, String link, int rawgId) {
		Game game = null;
		boolean preExistant = false;
		if((game = gameHandler.findByName(name)) != null) {
			preExistant = true;
		}else {
			game = new Game();
		}
		game.setName(name);
		game.setDescription(description);
		game.setLink(link);
		game.setRawgId(rawgId);
		if(preExistant) {
			gameHandler.update(game);
		}else {
			gameHandler.insert(game);
		}
	}
}