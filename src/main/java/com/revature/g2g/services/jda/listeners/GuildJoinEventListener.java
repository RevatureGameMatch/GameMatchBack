package com.revature.g2g.services.jda.listeners;

import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.invite.GenericGuildInviteEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
public class GuildJoinEventListener extends ListenerAdapter{
	@Override
	public void onGuildVoiceJoin(GuildVoiceJoinEvent event) {
		// TODO Auto-generated method stub
		super.onGuildVoiceJoin(event);
	}
}