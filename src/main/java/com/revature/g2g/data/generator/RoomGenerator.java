package com.revature.g2g.data.generator;

import java.util.Set;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.business.RoomService;
import com.revature.g2g.services.handlers.RoomHandler;

public class RoomGenerator implements DataGenerator {
	private static RoomHandler roomHandler = new RoomHandler();
	@Override
	public void generate() {
		Set<Room> activeRooms = roomHandler.findByStatus(RoomStatus.OPENED);
		int roomsToMake = 15 - activeRooms.size();
		for(int a=0; a<roomsToMake; a++) {
			RoomService.make();
		}
	}
}
