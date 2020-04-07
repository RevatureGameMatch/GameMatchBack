package com.revature.g2g.services.jda;

import java.util.List;

import javax.security.auth.login.LoginException;

import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PropertiesHelper;
import com.revature.g2g.services.jda.listeners.GuildVoiceLeaveEventListener;
import com.revature.g2g.services.jda.listeners.MessageListener;
import com.revature.g2g.services.jda.listeners.ReadyListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

public class JDASingleton {
	private static JDA jda;
	private static Guild guild;
	private static boolean ready = false;
	private JDASingleton() {
	}
	public static JDA getJda() {
		if(jda == null) {
			try {
				JDABuilder builder = JDABuilder.createDefault(PropertiesHelper.getPropValues().getProperty("discordKey"));
				builder.setActivity(Activity.watching("Users for new commands."));
				builder.addEventListeners(new ReadyListener());
				builder.addEventListeners(new MessageListener());
				builder.addEventListeners(new GuildVoiceLeaveEventListener());
				jda = builder.build();
			} catch (LoginException e) {
				jda = null;
				LoggerSingleton.getExceptionLogger().warn("JDASingleton: Discord Login failed. ", e);
			}
		}
		return jda;
	}
	public static Guild getGuild() {
		if(ready == false) {
			LoggerSingleton.getExceptionLogger().warn("JDA not ready.",new RuntimeException());
		}
		if(guild == null) {
			List<Guild> guilds = jda.getGuilds();
			for(Guild guildFound : guilds) {
				guild = guildFound;
			}
		}
		return guild;
	}
	public static boolean getReady() {
		return ready;
	}
	public static void setReady(boolean readyIn) {
		ready = readyIn;
	}
}
