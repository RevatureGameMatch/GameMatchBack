package com.revature.g2g.services.jda.helpers;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.AuditableRestAction;
import net.dv8tion.jda.api.requests.restaction.ChannelAction;

public class TextChannelHelper {
	private TextChannelHelper() {
	}
	public static ChannelAction<TextChannel> create(Guild guild, String name){
		return guild.createTextChannel(name);
	}
	public static AuditableRestAction<Void> delete(TextChannel channel){
		return channel.delete();
	}
}
