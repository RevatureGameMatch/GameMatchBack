package com.revature.g2g.services.helpers;

import javax.security.auth.login.LoginException;

import com.revature.g2g.services.jda.MessageListener;
import com.revature.g2g.services.jda.ReadyListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class JDASingleton {
	private static JDA jda;
	private JDASingleton() {
	}
	public static JDA getJda() {
		if(jda == null) {
			try {
				JDABuilder builder = JDABuilder.createDefault(PropertiesHelper.getPropValues().getProperty("discordKey"));
				builder.setActivity(Activity.watching("Users for new commands."));
				builder.addEventListeners(new ReadyListener());
				builder.addEventListeners(new MessageListener());
				jda = builder.build();
			} catch (LoginException e) {
				jda = null;
				LoggerSingleton.getExceptionLogger().warn("JDASingleton: Discord Login failed. ", e);
			}
		}
		return jda;
	}
}
