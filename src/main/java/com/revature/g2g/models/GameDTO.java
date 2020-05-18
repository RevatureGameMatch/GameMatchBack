package com.revature.g2g.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class GameDTO {
	private long gameId;
	private String name;
	private String link;
	private String description;
	private int rawgId;

	public GameDTO(Game game) {
		super();
		this.gameId = game.getGameId();
		this.name = game.getName();
		this.link = game.getLink();
		this.description = game.getDescription();
		this.rawgId = game.getRawgId();
	}

}
