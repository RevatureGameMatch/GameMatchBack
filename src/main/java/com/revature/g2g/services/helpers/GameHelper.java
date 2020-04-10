package com.revature.g2g.services.helpers;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;

@Service
public class GameHelper {
	public Game clean(Game game) {
		Game g = new Game();
		g.setDescription(Jsoup.clean(game.getDescription(), Whitelist.none()));
		g.setLink(Jsoup.clean(game.getLink(), Whitelist.none()));
		g.setName(Jsoup.clean(game.getName(), Whitelist.none()));
		return g;
	}
}
