package com.revature.g2g.services.jda.listeners;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
public class GuildVoiceEventListener extends ListenerAdapter{
	@Autowired
	private JDASingleton jDASingleton;
	private RoomHandler roomHandler;
	public GuildVoiceEventListener() {
		super();
	}
	public RoomHandler getRoomHandler() {
		return roomHandler;
	}
	@Autowired
	public GuildVoiceEventListener(RoomHandler roomHandler) {
		super();
		this.roomHandler = roomHandler;
	}
	public void setRoomHandler(RoomHandler roomHandler) {
		this.roomHandler = roomHandler;
	}
	@Override
	public void onGuildVoiceLeave(GuildVoiceLeaveEvent event) {
		processMoveLeave(event);
		super.onGuildVoiceLeave(event);
	}
	@Override
	public void onGuildVoiceMove(GuildVoiceMoveEvent event) {
		processMoveLeave(event);
		super.onGuildVoiceMove(event);
	}
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		// TODO Auto-generated method stub
		super.onGuildVoiceJoin(event);
	}
	private void processMoveLeave(GuildVoiceUpdateEvent event) {
		JDA jda = jDASingleton.getJda();
		if(event.getChannelLeft().getMembers().size() != 0)return;
		Room room = roomHandler.findRoomByDiscordVoice(event.getChannelLeft().getIdLong());
		if (room == null)return;
		room.setStatus(RoomStatus.CLOSED);
		room.setClosed(new Date());
		roomHandler.update(room);
		jda.getVoiceChannelById(room.getDiscordVoiceChannelId()).delete().queue();
		jda.getTextChannelById(room.getDiscordTextChannelId()).delete().queue();
	}
}