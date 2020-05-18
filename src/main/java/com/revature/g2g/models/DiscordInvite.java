package com.revature.g2g.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@Table(name = "G3G_DISCORD_INVITE")
@Getter @Setter @EqualsAndHashCode @ToString
public class DiscordInvite implements Serializable{
	private static final long serialVersionUID = 7444308037511438669L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "invite_id")
	private long id;
	
	@Column(name = "invite_username")
	private String username;
	
	@Column(name = "invite_discord_code")
	private String discordCode;
	
	@NotNull(message = "The DiscordInvite must havea a room.")
	@ManyToOne()
	@JoinColumn(name = "invite_room")
	private Room room;
	
	@NotNull(message = "The DiscordInvite must have a player.")
	@ManyToOne()
	@JoinColumn(name = "invite_player")
	private Player player;
	
	@Column(name="invite_status")
	private DiscordInviteStatus status;

	public DiscordInvite() {
		super();
		this.room = new Room();
		this.player = new Player();
	}
	
	public DiscordInvite(long id, String username, String discordCode, Room room, Player player,
			DiscordInviteStatus status) {
		super();
		this.id = id;
		this.username = username;
		this.discordCode = discordCode;
		this.room = room;
		this.player = player;
		this.status = status;
	}
	
	public DiscordInvite(DiscordInviteDTO source) {
		this();
		this.id = source.getId();
		this.username = source.getUsername();
		this.discordCode = source.getDiscordCode();
		this.room = new Room(source.getRoom() );
		this.player = new Player(source.getPlayer() );
		this.status = source.getStatus();
	}
}