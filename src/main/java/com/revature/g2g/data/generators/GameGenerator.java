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
		make("DnD5e", "Dungeons & Dragons 5th Ed. Classic TTRPG. Tell a story together, guide your heroes through quests for treasure, battle with deadly foes, daring rescues, courtly intrigue, and much more.", "https://dnd.wizards.com/",13482);
		make("Halo 5: Guardians", "A mysterious and unstoppable force threatens the galaxy. The Spartans of Fireteam Osiris and Blue Team must embark on a journey that will change the course of history and the future of mankind. First person shooter.", "https://www.halowaypoint.com/en-us/games/halo-infinite",8444);
		make("Call of Duty: Warzone", "Welcome to Warzone. Battle Royale. Drop in, armor up, loot for rewards, and battle your way to the top. First person shooter.", "https://www.callofduty.com/warzone",418467);
		make("Minecraft", "Explore your own unique world as you build, mine, battle mobs, and more. Sandbox.", "https://www.minecraft.net/en-us/",22509);
		make("Fortnite", "Battle to be the last one standing! Season 2: The Island has been taken over by covert operatives - members of Ghost and Shadow. Will you join the fight? Battle Royal.", "https://www.epicgames.com/fortnite/en-US/home",47137);
		make("League of Legends", "Play as a champion and take down the enemy Nexus. MOBA.", "https://play.na.leagueoflegends.com/en_US",23598);
		make("MapleStory 2", "Create your own character, embark on epic quests, team up to conquer dungeons and raids, and build your dream world. MMORPG.", "https://maplestory2.nexon.net/en",244721);
		make("Smite", "Become a legend of myth, enter the Battleground of the Gods, and smite your enemies. MOBA.", "https://www.smitegame.com/play-for-free/",752);
		make("Don't Starve Together", "Enter a strange and unexplored world full of strange creatures, dangers, and surprises. Work together to survive! Survival.", "https://www.klei.com/games/dont-starve-together",9882);
		make("Overcooked", "Out of the frying pan, into the fire… Save the world from the Ever Peckish. Play two chefs who travel back in time to refine their cooking skills. Simulation.", "http://www.ghosttowngames.com/overcooked/",2115);
		make("Keep Talking and Nobody Explodes", "One player has a bomb, the rest have the instructions. Can you defuse it in time?", "https://keeptalkinggame.com/",1073);
		make("Sea of Thieves", "Sail together, plunder together. Play a pirate and set out to explore the open sea. Adventure.", "https://www.seaofthieves.com/",50781);
		make("Warframe", "In the far-future world of Warframe, grotesque clones and capitalist machines dominate our solar system. Fight back against greed and corruption as you explore a world of techno-organic horrors. Third person shooter.", "https://www.warframe.com/",766);
		make("Elsword", "Bandits, monsters and demons threatened the population of Elrios – and all of them wanted only one thing: the shards of the grand EL. Are you hero enough to go up against them in battle? MMORPG", "https://elsword.koggames.com/",40774);
		make("Skyforge", "When the great god Aeli vanished, hostile gods and evil creatures invaded the planet. Become an Immortal and protect Aelion. MMORPG", "https://sf.my.games/en",21942);
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