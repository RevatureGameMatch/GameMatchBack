package com.revature.g2g.models;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "G3G_PLAYER_ROOM_JT")
@Getter @Setter @EqualsAndHashCode @ToString
public class PlayerRoomJT implements Serializable{
	private static final long serialVersionUID = -7131529712503037883L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "player_room_jt_id")
	private long playerRoomJTId;
	
	@NotNull(message="PlayerRoomJT requires a player.")
	@ManyToOne()
	@JoinColumn(name = "player_id")
	private Player player;
	
	@NotNull(message="PlayerRoomJT requires a room.")
	@ManyToOne()
	@JoinColumn(name = "room_id")
	private Room room;
	
	@Column(name = "joined")
	private Date joined;
	
	@Column(name = "left_room")
	private Date left;

	public PlayerRoomJT() {
		super();
		this.player = new Player();
		this.room = new Room();
	}
	
	public PlayerRoomJT(int playerRoomJTId, Player player, Room room, Date joined, Date left) {
		super();
		this.playerRoomJTId = playerRoomJTId;
		this.player = player;
		this.room = room;
		this.joined = joined;
		this.left = left;
	}
	
	public PlayerRoomJT(PlayerRoomDTO source) {
		this();
		this.playerRoomJTId = source.getPlayerRoomId();
		this.player = new Player(source.getPlayer() );
		this.room = new Room(source.getRoom() );
		this.joined = source.getJoined();
		this.left = source.getLeft();
	}
}