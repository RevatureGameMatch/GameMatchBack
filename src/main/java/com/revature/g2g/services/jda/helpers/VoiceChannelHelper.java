package com.revature.g2g.services.jda.helpers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class VoiceChannelHelper {
	private VoiceChannelHelper() {
	}
	public static ChannelAction<VoiceChannel> create(Guild guild, String name){
		return guild.createVoiceChannel(name);
	}
	public static AuditableRestAction<Void> delete(VoiceChannel channel){
		return channel.delete();
	}
}
