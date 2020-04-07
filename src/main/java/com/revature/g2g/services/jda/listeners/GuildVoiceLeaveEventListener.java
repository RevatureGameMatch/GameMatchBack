package com.revature.g2g.services.jda.listeners;

import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class GuildVoiceLeaveEventListener extends ListenerAdapter{
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
	private void processMoveLeave(GuildVoiceUpdateEvent event) {
		if(event.getChannelLeft().getMembers().size() != 0)return;
		//TODO lookup room by id then delete it and its text channel, 
//		then close the room
	}
}