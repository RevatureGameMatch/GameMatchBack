package com.revature.g2g.services.business;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.RoomHandler;

@Service
public class RoomService {
	private static RoomHandler roomHandler = new RoomHandler();
	private RoomService() {
	}
	public static Room make() {
		Room room = new Room();
		room.setCreated(new Date());
		room.setStatus(RoomStatus.OPENED);
		roomHandler.insert(room);
		return room;
	}
}
