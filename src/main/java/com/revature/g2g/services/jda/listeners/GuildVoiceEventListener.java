package com.revature.g2g.services.jda.listeners;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Room;
import com.revature.g2g.models.RoomStatus;
import com.revature.g2g.services.handlers.RoomHandler;
import com.revature.g2g.services.jda.JDASingleton;
import com.revature.g2g.services.jda.helpers.RoleHelper;
import com.revature.g2g.services.jda.helpers.TextChannelHelper;
import com.revature.g2g.services.jda.helpers.VoiceChannelHelper;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
public class GuildVoiceEventListener extends ListenerAdapter{
	@Autowired
	private RoomHandler roomHandler;
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
		JDA jda = JDASingleton.getJda();
		if(event.getChannelLeft().getMembers().size() != 0)return;
		Room room = roomHandler.findRoomByDiscordVoice(event.getChannelLeft().getIdLong());
		if (room == null)return;
		if (room.getStatus().equals(RoomStatus.CLOSED))return;
		room.setStatus(RoomStatus.CLOSED);
		room.setClosed(new Date());
		roomHandler.update(room);
//		jda.getVoiceChannelById(room.getDiscordVoiceChannelId()).delete();
//		jda.getTextChannelById(room.getDiscordTextChannelId()).delete();
		RoleHelper.delete(jda.getRoleById(room.getDiscordRoleId()));
		TextChannelHelper.delete(jda.getTextChannelById(room.getDiscordTextChannelId()));
		VoiceChannelHelper.delete(jda.getVoiceChannelById(room.getDiscordVoiceChannelId()));
	}
}