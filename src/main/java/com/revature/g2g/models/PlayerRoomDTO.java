package com.revature.g2g.models;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class PlayerRoomDTO {
	private long playerRoomId;
	private PlayerDTO player;
	private PlayerDTO sender;
	private RoomDTO room;
	private Date joined;
	private Date left;
	
	public PlayerRoomDTO(PlayerRoomJT source) {
		this.playerRoomId = source.getPlayerRoomJTId();
		this.player = new PlayerDTO(source.getPlayer() );
		this.room = new RoomDTO(source.getRoom() );
		this.joined = source.getJoined();
		this.left = source.getLeft();
	}

	public PlayerRoomDTO(PlayerRoomDTO source) {
		this.playerRoomId = source.getPlayerRoomId();
		this.player = new PlayerDTO(source.getPlayer() );
		this.sender = new PlayerDTO(source.getSender() );
		this.room = new RoomDTO(source.getRoom() );
		this.joined = source.getJoined();
		this.left = source.getLeft();
	}

}
