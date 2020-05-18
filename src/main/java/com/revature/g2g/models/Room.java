package com.revature.g2g.models;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@JsonIgnoreProperties(value = { "playerJT", "roomChangesToSkill", "skills" })
@Entity
@Table(name = "G3G_ROOM")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(exclude = {"playerJT","roomChangesToSkill","skills"}) @ToString(exclude = {"playerJT","roomChangesToSkill","skills"})
public class Room implements Serializable{
	private static final long serialVersionUID = -5444471863298926133L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "room_id")
	private long roomId;
	
	@Column(name = "discord_text_channel_id")
	private long discordTextChannelId;
	
	@Column(name = "discord_voice_channel_id")
	private long discordVoiceChannelId;
	
	@Column(name = "discord_role_id")
	private long discordRoleId;
	
	@Column(name = "room_created")
	private Date created;
	
	@Column(name = "room_closed")
	private Date closed;
	
	@Column(name = "room_name")
	private String name;
	
	@Column(name= "room_current_players")
	private int currentPlayers;
	
	@Column(name = "room_max_players")
	private int maxPlayers;
	
	@Column(name = "room_description")
	private String description;
	
	@Column(name = "room_status")
	private RoomStatus status;
	
	@Column(name = "room_play_style")
	private RoomPlayStyle style;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "room_game")
	private Game game;
	
	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private Set<PlayerRoomJT> playerJT = new HashSet<>();

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> roomChangesToSkill = new HashSet<>();

	@OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
	private Set<SkillRoomJT> skills = new HashSet<>();

	public Room(String name, int currentPlayers, int maxPlayers, String description, RoomPlayStyle style, Game game) {
		super();
		this.name = name;
		this.currentPlayers = currentPlayers;
		this.maxPlayers = maxPlayers;
		this.description = description;
		this.style = style;
		this.game = game;
	}
	
	public Room(Long discordTextChannelId, Long discordVoiceChannelId, Long discordRoleId, Date created,
			String description, RoomStatus status, RoomPlayStyle style) {
		super();
		this.discordTextChannelId = discordTextChannelId;
		this.discordVoiceChannelId = discordVoiceChannelId;
		this.discordRoleId = discordRoleId;
		this.created = created;
		this.description = description;
		this.status = status;
		this.style = style;
	}
	
	public Room(RoomDTO source) {
		this();
		this.roomId = source.getRoomId();
		this.discordTextChannelId = source.getDiscordTextChannelId();
		this.discordVoiceChannelId = source.getDiscordVoiceChannelId();
		this.discordRoleId = source.getDiscordRoleId();
		this.created = source.getCreated();
		this.closed = source.getClosed();
		this.name = source.getName();
		this.currentPlayers =  source.getCurrentPlayers();
		this.maxPlayers = source.getMaxPlayers();
		this.description = source.getDescription();
		this.status = source.getStatus();
		this.style = source.getStyle();
		this.game = new Game(source.getGame() );
	}
}