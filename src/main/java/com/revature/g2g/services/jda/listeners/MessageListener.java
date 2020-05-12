package com.revature.g2g.services.jda.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.models.Game;
import com.revature.g2g.models.RoomPlayStyle;
import com.revature.g2g.services.handlers.GameHandler;
import com.revature.g2g.services.helpers.DiscordHelper;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.RoomHelper;
import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.entities.ChannelType;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MessageListener extends ListenerAdapter{
	private LoggerSingleton loggerSingleton;
	private RoomHelper roomHelper;
	private DiscordHelper discordHelper;
	private GameHandler gameHandler;
	@Autowired
	public MessageListener(LoggerSingleton loggerSingleton, RoomHelper roomHelper, DiscordHelper discordHelper,
			GameHandler gameHandler, JDASingleton jdaSingleton) {
		super();
		this.loggerSingleton = loggerSingleton;
		this.roomHelper = roomHelper;
		this.discordHelper = discordHelper;
		this.gameHandler = gameHandler;
		jdaSingleton.getJda().addEventListener(this);
	}
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
		if(discordHelper.adminCheck(qualifiedUsername)) {
			try {
				String msg = event.getMessage().getContentRaw();
				String name = msg.substring(msg.indexOf(' '));
				Game other = gameHandler.findByName("Other");
				roomHelper.insert(name, RoomPlayStyle.CASUAL, other,5,0, other.getDescription());
			}catch (Exception e) {
				loggerSingleton.getExceptionLogger().warn("Exception from chat creating new voice channel: ", e);
			}
		}else {
			MessageChannel channel = event.getChannel();
			channel.sendMessage("That command is for Admins only!")
				.queue();
		}
	}
}
