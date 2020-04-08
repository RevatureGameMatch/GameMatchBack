package com.revature.g2g.services.jda.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.services.business.RoomService;
import com.revature.g2g.services.helpers.LoggerSingleton;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessageListener extends ListenerAdapter{
	@Autowired
	private LoggerSingleton loggerSingleton;
	@Autowired
	private RoomService roomService;
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		if(event.isFromType(ChannelType.PRIVATE)) {
			String message1 = String.format("[PM] %s: %s", event.getAuthor().getName(), event.getMessage().getContentDisplay());
			loggerSingleton.getDiscordLog().trace(message1);
		}else {
			String message2 = String.format("[%s][%s] %s: %s", event.getGuild().getName(), 
					event.getTextChannel().getName(), event.getMember().getEffectiveName(), event.getMessage().getContentDisplay());
			loggerSingleton.getDiscordLog().trace(message2);
		}
		Message msg = event.getMessage();
		if(event.getAuthor().isBot())return;
		if(msg.getContentRaw().equals("!ping")) {
			ping(event);
		}else if(msg.getContentRaw().startsWith("!new")){
			newChannel(event);
		}
	}
	private void ping(MessageReceivedEvent event) {
		MessageChannel channel = event.getChannel();
		long time = System.currentTimeMillis();
		channel.sendMessage("Pong2!")
			.queue(response ->
				response.editMessageFormat("Pong2: %d ms", System.currentTimeMillis() - time).queue()
			);
	}
	private void newChannel(MessageReceivedEvent event) {
		String qualifiedUsername = (event.getAuthor().getName() + "#" + event.getAuthor().getDiscriminator());
		System.out.println(qualifiedUsername);
		if(! (qualifiedUsername.equals("ProNobis#7047")||qualifiedUsername.equals("kfilio#6124")||qualifiedUsername.equals("shotofthewritten#5186")))
		try {
			String msg = event.getMessage().getContentRaw();
			String name = msg.substring(msg.indexOf(' '));
			roomService.make(name, RoomPlayStyle.CASUAL);
		}catch (Exception e) {
			loggerSingleton.getExceptionLogger().warn("Exception from chat creating new voice channel: ", e);
		}
	}
}
