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
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.revature.g2g.api.templates.PlayerTemplate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@JsonIgnoreProperties(value = {"roomJT","changesToSelf","changesToOthers","skills"})
@Entity
@Table(name = "G3G_PLAYER")
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode(exclude = {"roomJT","changesToSelf","changesToOthers","skills"}) @ToString(exclude = {"roomJT","changesToSelf","changesToOthers","skills"})
public class Player implements Serializable {
	private static final long serialVersionUID = 5673274454063809268L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	@Column(name = "player_id")
	private long playerId;
	
	@Size(min=3, max=20, message="The username must be between 3 and 25 characters.")
	@Column(name = "player_username", unique = true)
	private String playerUsername;
	
	@Email(message="You must submit a valid email. This will be used for account recovery.")
	@Column(name = "player_email", unique = true)
	private String playerEmail;
	
	@Size(min=5, max=100, message="Your password must be between 5 and 100 characters.")
	@Column(name = "player_password")
	private String playerPassword;
	
	@Column(name = "player_role")
	private PlayerRole  playerRole;
	
	@JsonIgnore
	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<PlayerRoomJT> roomJT = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> changesToSelf = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "modifiedBy", fetch = FetchType.LAZY)
	private Set<SkillPlayerChangeJT> changesToOthers = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "player", fetch = FetchType.LAZY)
	private Set<SkillPlayerJT> skills = new HashSet<>();

	public Player(int playerId, String playerUsername, String playerEmail, String playerPassword,
			PlayerRole playerRole) {
		super();
		this.playerId = playerId;
		this.playerUsername = playerUsername;
		this.playerEmail = playerEmail;
		this.playerPassword = playerPassword;
		this.playerRole = playerRole;
	}
	
	public Player(PlayerTemplate template) {
		super();
		this.playerId = template.getPlayerId();
		this.playerUsername = Jsoup.clean(template.getPlayerUsername(), Whitelist.none());
		this.playerEmail = Jsoup.clean(template.getPlayerEmail(), Whitelist.none());
		this.playerPassword = Jsoup.clean(template.getPlayerPassword(), Whitelist.none());
		PlayerRole role = template.getPlayerRole();
		if(role instanceof PlayerRole) {
			this.playerRole = role;
		}else {
			this.playerRole = PlayerRole.PLAYER;
		}
	}
	
	public Player(PlayerDTO source) {
		this();
		this.playerId = source.getPlayerId();
		this.playerUsername = source.getPlayerUsername();
		this.playerEmail = source.getPlayerEmail();
		this.playerRole = source.getPlayerRole();
	}
}