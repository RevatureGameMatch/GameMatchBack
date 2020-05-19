package com.revature.g2g.models;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class GameDTO {
	private long gameId;
	private String name;
	private String link;
	private String description;
	private int rawgId;
	private PlayerDTO sender;

	public GameDTO(Game source) {
		super();
		this.setGameId(source.getGameId() );
		this.setName(Jsoup.clean(source.getName(), Whitelist.none()) );
		this.setLink(Jsoup.clean(source.getLink(), Whitelist.none()) );
		this.setDescription(Jsoup.clean(source.getDescription(), Whitelist.none()) );
		this.setRawgId(source.getRawgId() );
	}

	public GameDTO(GameDTO source) {
		super();
		this.setGameId(source.getGameId() );
		this.setName(Jsoup.clean(source.getName(), Whitelist.none()) );
		this.setLink(Jsoup.clean(source.getLink(), Whitelist.none()) );
		this.setDescription(Jsoup.clean(source.getDescription(), Whitelist.none()) );
		this.setRawgId(source.getRawgId() );
		this.setSender(source.getSender() );
	}

}
