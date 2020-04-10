package com.revature.g2g.data.generators;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Game;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.RoomHelper;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoomGenerator implements DataGenerator {
	private RoomHandler roomHandler;
	private RoomHelper roomHelper;
	private LoggerSingleton loggerSingleton;
	private Set<Game> games;
	@Autowired
	public RoomGenerator(RoomHandler roomHandler, RoomHelper roomHelper, GameHandler gameHandler, LoggerSingleton loggerSingleton) {
		super();
		this.roomHandler = roomHandler;
		this.roomHelper = roomHelper;
		games = gameHandler.findAll();
	}
	@Override
	public void generate() {
		Set<Room> activeRooms = roomHandler.findByStatus(RoomStatus.JOINING);
		int roomsToMake = 15 - activeRooms.size() + 1;
		for(int a=1; a<roomsToMake; a++) {
			Game game = randomGame();
			if(game != null) {
				roomHelper.insert(game.getName() + " #" + a, randomStyle(), game, new Random().nextInt(7)+3, new Random().nextInt(3), game.getDescription());
			}
		}
	}
	private static RoomPlayStyle randomStyle() {
		RoomPlayStyle[] styles = RoomPlayStyle.values();
		int count = styles.length;
		return styles[new Random().nextInt(count)];
	}
	private Game randomGame() {
		int count = games.size();
		if(count != 0) {
			int random = new Random().nextInt(count);
			return (Game) games.toArray()[random];
		}else {
			loggerSingleton.getExceptionLogger().warn("RoomGenerator: Random game count of 0");
		}
		return null;
	}
}
