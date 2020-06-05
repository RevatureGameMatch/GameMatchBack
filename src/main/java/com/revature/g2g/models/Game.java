package com.revature.g2g.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@JsonIgnoreProperties(value = { "skills" })
@Entity
@Table(name = "G2G_GAME")
@Getter @Setter @EqualsAndHashCode(exclude = {"skills"}) @ToString(exclude = {"skills"})
public class Game implements Serializable{
	private static final long serialVersionUID = 2159855250913785420L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "game_id")
	private long gameId;
	
	@Size(min=5, max=50, message="Game Name must be between 5 and 50 characters")
	@Column(name = "game_name", unique = true)
	private String name;
	
	@NotNull(message="Game must have a link")
	@Column(name = "game_link")
	private String link;
	
	@Column(name = "game_description")
	private String description;
	
	@NotNull(message = "Game must have a rawgId and it must be an int.")
	@Column(name = "game_rawg_id")
	private int rawgId;

	@OneToMany(mappedBy = "game", fetch = FetchType.LAZY)
	private Set<SkillGameJT> skills = new HashSet<>();
	
	public Game() {
		super();
		this.link = "";
		this.rawgId = 158;
	}
	
	public Game(long gameId, String name, String link, String description) {
		super();
		this.gameId = gameId;
		this.name = name;
		this.link = link;
		this.description = description;
		this.rawgId = 158;
	}
	
	public Game(long gameId, String name, String link, String description, int rawgId, Set<SkillGameJT> skills) {
		super();
		this.gameId = gameId;
		this.setName(Jsoup.clean(name, Whitelist.none()) );
		this.setLink(Jsoup.clean(link, Whitelist.none()) );
		this.setDescription(Jsoup.clean(description, Whitelist.none()) );
		this.rawgId = rawgId;
		this.skills = skills;
	}

	public Game(Game source) {
		this.gameId = source.getGameId();
		this.setName(Jsoup.clean(source.getName(), Whitelist.none()) );
		this.setLink(Jsoup.clean(source.getLink(), Whitelist.none()) );
		this.setDescription(Jsoup.clean(source.getDescription(), Whitelist.none()) );
		this.rawgId = source.getRawgId();
	}
	
	public Game(GameDTO source) {
		this();
		this.gameId = source.getGameId();
		this.setName(Jsoup.clean(source.getName(), Whitelist.none()) );
		this.setLink(Jsoup.clean(source.getLink(), Whitelist.none()) );
		this.setDescription(Jsoup.clean(source.getDescription(), Whitelist.none()) );
		this.rawgId = source.getRawgId();
	}

	public Game(Optional<Game> source) {
		this(source.get());
	}

}