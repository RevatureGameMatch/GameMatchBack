package com.revature.g2g.models;

import com.revature.g2g.services.helpers.SanitizerHelper;

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
		this.setName(source.getName() );
		this.setLink(source.getLink() );
		this.setDescription(source.getDescription() );
		this.setRawgId(source.getRawgId() );
	}

	public GameDTO(GameDTO source) {
		super();
		this.setGameId(source.getGameId() );
		this.setName(source.getName() );
		this.setLink(source.getLink() );
		this.setDescription(source.getDescription() );
		this.setRawgId(source.getRawgId() );
		this.setSender(source.getSender() );
	}
	
	public void setDescription(String details) {
		this.description = SanitizerHelper.sanitize(details);
	}
	
	public void setLink(String neoLink) {
		this.link = SanitizerHelper.sanitize(neoLink);
	}

	public void setName(String neoName) {
		this.name = SanitizerHelper.sanitize(neoName);
	}
}
