package com.revature.g2g.services.jda.helpers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class VoiceChannelHelper {
	public static void create(Guild guild, String name) {
		ChannelAction<VoiceChannel> action = guild.createVoiceChannel(name);
		action.queue();
	}
}
