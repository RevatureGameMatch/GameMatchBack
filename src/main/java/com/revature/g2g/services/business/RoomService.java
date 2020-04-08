package com.revature.g2g.services.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.jda.JDASingleton;
import com.revature.g2g.services.jda.helpers.TextChannelHelper;
import com.revature.g2g.services.jda.helpers.VoiceChannelHelper;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

@Service
public class RoomService {
	@Autowired
	private JDASingleton jDASingleton;
	private RoomHandler roomHandler;
	public RoomService() {
		super();
	}
	@Autowired
	public RoomService(RoomHandler roomHandler) {
		super();
		this.roomHandler = roomHandler;
	}
	public RoomHandler getRoomHandler() {
		return roomHandler;
	}
	public void setRoomHandler(RoomHandler roomHandler) {
		this.roomHandler = roomHandler;
	}
	public Room make(String name, RoomPlayStyle style) {
		Guild guild = jDASingleton.getGuild();
		Room room = new Room();
		room.setCreated(new Date());
		room.setStatus(RoomStatus.OPENED);
		room.setStyle(style);
		ChannelAction<VoiceChannel> voiceChannel = VoiceChannelHelper.create(guild, name);
		ChannelAction<TextChannel> textChannel = TextChannelHelper.create(guild, name);
		room.setDiscordVoiceChannelId(voiceChannel.complete().getIdLong());
		room.setDiscordTextChannelId(textChannel.complete().getIdLong());
		this.roomHandler.insert(room);
		return room;
	}
}
