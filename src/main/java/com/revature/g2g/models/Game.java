package com.revature.g2g.models;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "G2G_GAME")
public class Game implements Serializable{
	private static final long serialVersionUID = 2159855250913785420L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "game_id")
	private int gameId;
	
	@Column(name = "game_name", unique = true)
	private String name;
	
	@Column(name = "game_link")
	private String link;
	
	@Column(name = "game_description")
	private String description;

	@OneToMany(mappedBy = "game_id", fetch = FetchType.LAZY)
	private Set<SkillGameJT> skills = new HashSet<>();
	
	public Game() {
		super();
	}
	public Game(int gameId, String name, String link, String description) {
		super();
		this.gameId = gameId;
		this.name = name;
		this.link = link;
		this.description = description;
	}
	public int getGameId() {
		return gameId;
	}
	public void setGameId(int gameId) {
		this.gameId = gameId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<SkillGameJT> getSkills() {
		return skills;
	}
	public void setSkills(Set<SkillGameJT> skills) {
		this.skills = skills;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + gameId;
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Game)) {
			return false;
		}
		Game other = (Game) obj;
		if (description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (gameId != other.gameId) {
			return false;
		}
		if (link == null) {
			if (other.link != null) {
				return false;
			}
		} else if (!link.equals(other.link)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	@Override
	public String toString() {
		return "Game [gameId=" + gameId + ", name=" + name + ", link=" + link + ", description=" + description + "]";
	}
}