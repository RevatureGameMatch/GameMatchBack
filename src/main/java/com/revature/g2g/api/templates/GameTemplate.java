package com.revature.g2g.api.templates;

import java.util.Objects;

import javax.validation.constraints.NotNull;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.PlayerDTO;

public class GameTemplate {
	@NotNull
	private Game game;
	@NotNull
	private PlayerDTO sender;
	public GameTemplate() {
		super();
		this.game = new Game();
		this.sender = new PlayerDTO();
	}
	public GameTemplate(Game game, PlayerTemplate sender) {
		super();
		this.game = game;
		this.sender = new PlayerDTO(sender);
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public PlayerDTO getSender() {
		return sender;
	}
	public void setSender(PlayerTemplate sender) {
		this.sender = new PlayerDTO(sender);
	}
	@Override
	public int hashCode() {
		return Objects.hash(sender, game);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof GameTemplate)) {
			return false;
		}
		GameTemplate other = (GameTemplate) obj;
		return Objects.equals(sender, other.sender) && Objects.equals(game, other.game);
	}
	@Override
	public String toString() {
		return "GameTemplate [game=" + game + ", sender=" + sender + "]";
	}
}