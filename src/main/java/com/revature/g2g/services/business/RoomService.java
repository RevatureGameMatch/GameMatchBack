package com.revature.g2g.services.business;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.jda.JDASingleton;
import com.revature.g2g.services.jda.helpers.TextChannelHelper;
import com.revature.g2g.services.jda.helpers.VoiceChannelHelper;

import net.dv8tion.jda.api.entities.Guild;

@Service
public class RoomService {
	private static RoomHandler roomHandler = new RoomHandler();
	private static Guild guild = JDASingleton.getGuild();
	private RoomService() {
	}
	public static Room make(String name) {
		Room room = new Room();
		room.setCreated(new Date());
		room.setStatus(RoomStatus.OPENED);
		VoiceChannelHelper.create(guild, name);
		TextChannelHelper.create(guild, name);
		roomHandler.insert(room);
		return room;
	}
}
