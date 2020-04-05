package com.revature.g2g.data.generators;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Game;
import com.revature.g2g.services.handlers.GameHandler;

public class GameGenerator implements DataGenerator {
	private static GameHandler gameHandler = new GameHandler();
	@Override
	public void generate() {
		make("DnD5e", "Classic TTRPG to go adventuring with friends", "https://dnd.wizards.com/");
		make("Halo", "Hunt your enemies", "https://www.halowaypoint.com/en-us");
		make("Call of Duty", "Hunt your enemies", "https://www.callofduty.com/");
		make("Minecraft", "Build together, stay together.", "https://www.minecraft.net/en-us/");
		make("Fortnite", "Massive Battle Royal", "https://www.epicgames.com/fortnite/en-US/home");
		make("League of Legends", "Take down the Nexus", "https://play.na.leagueoflegends.com/en_US");
		make("Maple Story", "Adventure Together", "http://maplestory.com/en");
		make("Smite", "Smite your enemies", "https://www.smitegame.com/play-for-free/");
		make("Don't Starve Together", "Work together to survive!", "https://www.klei.com/games/dont-starve-together");
		make("Overcooked", "Can your culinary skills satisfy the customers?", "http://www.ghosttowngames.com/overcooked/");
		make("Keep Talking and Nobody Explodes", "One player has a bomb, the rest have the instructions. Can you defuse it in time?", "https://keeptalkinggame.com/");
		make("Sea of Thieves", "Sail together, plunder together", "https://www.seaofthieves.com/");
	}
	private void make(String name, String description, String link) {
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
		if(preExistant) {
			gameHandler.update(game);
		}else {
			gameHandler.insert(game);
		}
	}
}