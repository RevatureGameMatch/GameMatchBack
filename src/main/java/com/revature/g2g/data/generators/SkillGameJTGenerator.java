package com.revature.g2g.data.generators;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.data.gentemplates.SkillPriorityTemplate;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Skill;
import com.revature.g2g.models.SkillGameJT;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.SkillGameJTHandler;
import com.revature.g2g.services.handlers.SkillHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SkillGameJTGenerator implements DataGenerator {
	private GameHandler gameHandler;
	private SkillHandler skillHandler;
	private SkillGameJTHandler skillGameJTHandler;
	private LoggerSingleton loggerSingleton;
	private String coach = "Coach";
	private String communicator = "Communicator";
	private String empower = "Empower Team Decisions";
	private String family = "Family Friendly";
	private String interest = "Interest in Others Success";
	private String listener = "Listener";
	private String productive = "Productive";
	private String roleplay = "Roleplay";
	private String strategic = "Strategic";
	@Autowired
	public SkillGameJTGenerator(GameHandler gameHandler, SkillHandler skillHandler,
			SkillGameJTHandler skillGameJTHandler, LoggerSingleton loggerSingleton) {
		super();
		this.gameHandler = gameHandler;
		this.skillHandler = skillHandler;
		this.skillGameJTHandler = skillGameJTHandler;
		this.loggerSingleton = loggerSingleton;
	}
	@Override
	public void generate() {
		List<Game> games = gameHandler.findAll();
		for(Game game: games) {
			assignSkillsToGame(game.getName(), new SkillPriorityTemplate[] {
				new SkillPriorityTemplate("Fun to Play With",5),
				new SkillPriorityTemplate("Good at Game", 5),
			});
		}
		assignSkillsToGame("DnD5e", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 4),
			new SkillPriorityTemplate(empower, 7),
			new SkillPriorityTemplate(interest, 8),
			new SkillPriorityTemplate(listener, 8),
			new SkillPriorityTemplate(productive, 3),
			new SkillPriorityTemplate(roleplay,7),
			new SkillPriorityTemplate(strategic, 4)
		});
		assignSkillsToGame("Halo 5", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 4),
			new SkillPriorityTemplate(listener, 8),
			new SkillPriorityTemplate(productive, 7),
			new SkillPriorityTemplate(strategic, 4)
		});
		assignSkillsToGame("Call of Duty: Warzone", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 4),
			new SkillPriorityTemplate(listener, 8),
			new SkillPriorityTemplate(productive, 7),
			new SkillPriorityTemplate(strategic, 4)
		});
		assignSkillsToGame("Minecraft", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 3),
			new SkillPriorityTemplate(empower, 7),
			new SkillPriorityTemplate(interest, 6),
			new SkillPriorityTemplate(listener, 3),
			new SkillPriorityTemplate(productive, 8),
			new SkillPriorityTemplate(strategic, 3)
		});
		assignSkillsToGame("Fortnite", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 3),
			new SkillPriorityTemplate(empower, 7),
			new SkillPriorityTemplate(interest, 6),
			new SkillPriorityTemplate(listener, 3),
			new SkillPriorityTemplate(productive, 8),
			new SkillPriorityTemplate(strategic, 3)
		});
		assignSkillsToGame("League of Legends", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(interest, 7),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(productive, 8),
			new SkillPriorityTemplate(strategic, 6)
		});
		assignSkillsToGame("MapleStory 2", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(empower, 3),
			new SkillPriorityTemplate(family, 3),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(roleplay, 5),
			new SkillPriorityTemplate(strategic, 6)
		});
		assignSkillsToGame("Smite", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(strategic, 3)
		});
		assignSkillsToGame("Don't Starve Together", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 5),
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(empower, 3),
			new SkillPriorityTemplate(family, 3),
			new SkillPriorityTemplate(interest, 6),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(productive, 4),
			new SkillPriorityTemplate(strategic, 5)
		});
		assignSkillsToGame("Overcooked", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(empower, 3),
			new SkillPriorityTemplate(family, 3),
			new SkillPriorityTemplate(interest, 7),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(productive, 3),
			new SkillPriorityTemplate(strategic, 3)
		});
		assignSkillsToGame("Keep Talking and Nobody Explodes", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(interest, 7),
			new SkillPriorityTemplate(listener, 5),
		});
		assignSkillsToGame("Sea of Thieves", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(empower, 3),
			new SkillPriorityTemplate(interest, 7),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(productive, 3),
			new SkillPriorityTemplate(roleplay, 3),
			new SkillPriorityTemplate(strategic, 3)
		});
		assignSkillsToGame("Warframe", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(empower, 3),
			new SkillPriorityTemplate(interest, 7),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(productive, 3),
			new SkillPriorityTemplate(strategic, 3)
		});
		assignSkillsToGame("Elsword", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(empower, 3),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(roleplay, 3),
			new SkillPriorityTemplate(strategic, 3)
		});
		assignSkillsToGame("Skyforge", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(roleplay, 3),
		});
		assignSkillsToGame("TERA", new SkillPriorityTemplate[] { 
			new SkillPriorityTemplate(coach, 3),
			new SkillPriorityTemplate(communicator, 5),
			new SkillPriorityTemplate(listener, 5),
			new SkillPriorityTemplate(productive, 3),
			new SkillPriorityTemplate(strategic, 3)
		});
	}
	private boolean assignSkillsToGame(String gameName, SkillPriorityTemplate[] skills) {
		Game game = gameHandler.findByName(gameName);
		if(game == null) {
			loggerSingleton.getExceptionLogger().trace(String.format("SkillGameJTGenerator, game: %s not found.", gameName));
			return false;
		}
		for (SkillPriorityTemplate skillTemplate : skills) {
			Skill skill = skillHandler.findByName(skillTemplate.getSkill());
			if(skill == null) {continue;}
			SkillGameJT skillGameJT = null;
			if((skillGameJT = skillGameJTHandler.findBySkillAndGame(skill, game)) == null) {
				skillGameJT = new SkillGameJT();
				skillGameJT.setGame(game);
				skillGameJT.setSkill(skill);
			}
			skillGameJT.setRelevance(skillTemplate.getPriority());
			skillGameJTHandler.save(skillGameJT);
		}
		return true;
	}
}