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
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

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
//		CompletableFuture<VoiceChannel> voiceChannel = VoiceChannelHelper.create(guild, name, room);
//		CompletableFuture<TextChannel> textChannel = TextChannelHelper.create(guild, name, room);
//		CompletableFuture<Void> allReady = CompletableFuture.allOf(voiceChannel, textChannel);
//		allReady.then
		ChannelAction<VoiceChannel> voiceChannel = VoiceChannelHelper.create(guild, name);
		ChannelAction<TextChannel> textChannel = TextChannelHelper.create(guild, name);
		room.setDiscordVoiceChannelId(voiceChannel.complete().getIdLong());
		room.setDiscordTextChannelId(textChannel.complete().getIdLong());
		roomHandler.insert(room);
		return room;
	}
}
