package com.revature.g2g.services.jda;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class MessageListener extends ListenerAdapter{
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		Message msg = event.getMessage();
		if(msg.getContentRaw().equals("!ping")) {
			MessageChannel channel = event.getChannel();
			long time = System.currentTimeMillis();
			channel.sendMessage("Pong!")
				.queue(response ->{
					response.editMessageFormat("Pong: %d ms", System.currentTimeMillis() - time).queue();
				});
		}
		if(event.isFromType(ChannelType.PRIVATE)) {
			System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
		}else {
			System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(), 
					event.getTextChannel().getName(), event.getMember().getEffectiveName(), event.getMessage().getContentDisplay());
		}
	}
}
