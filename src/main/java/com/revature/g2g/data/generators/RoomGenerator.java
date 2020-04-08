package com.revature.g2g.data.generators;

import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.data.DataGenerator;
import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.business.RoomService;
import com.revature.g2g.services.handlers.RoomHandler;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RoomGenerator implements DataGenerator {
	private RoomHandler roomHandler;
	private RoomService roomService;
	public RoomGenerator() {
		super();
	}
	@Autowired
	public RoomGenerator(RoomHandler roomHandler, RoomService roomService) {
		super();
		this.roomHandler = roomHandler;
		this.roomService = roomService;
	}
	public RoomHandler getRoomHandler() {
		return roomHandler;
	}
	public void setRoomHandler(RoomHandler roomHandler) {
		this.roomHandler = roomHandler;
	}
	public RoomService getRoomService() {
		return roomService;
	}
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}
	@Override
	public void generate() {
		Set<Room> activeRooms = roomHandler.findByStatus(RoomStatus.OPENED);
		int roomsToMake = 5 - activeRooms.size() + 1;
		for(int a=1; a<roomsToMake; a++) {
			roomService.make("Test Room #" + a, randomStyle());
		}
	}
	private static RoomPlayStyle randomStyle() {
		RoomPlayStyle[] styles = RoomPlayStyle.values();
		int count = styles.length;
		return styles[new Random().nextInt(count)];
	}
}
