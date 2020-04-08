package com.revature.g2g.services.jda;

import javax.security.auth.login.LoginException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PropertiesHelper;
import com.revature.g2g.services.jda.listeners.GuildVoiceEventListener;
import com.revature.g2g.services.jda.listeners.MessageListener;
import com.revature.g2g.services.jda.listeners.ReadyListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

public class JDASingleton {
	private JDASingleton() {
	}
	private static JDA jda;
	public static JDA getJda() {
		if(jda == null) {
			try {
				ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
				JDABuilder builder = JDABuilder.createDefault(new PropertiesHelper().getPropValues().getProperty("discordKey"));
				builder.setActivity(Activity.watching("Users for new commands."));
				builder.addEventListeners(ac.getBean(GuildVoiceEventListener.class));
				builder.addEventListeners(ac.getBean(MessageListener.class));
				builder.addEventListeners(ac.getBean(ReadyListener.class));
				jda = builder.build();
				new LoggerSingleton().getDiscordLog().trace("JDA building");
			} catch (LoginException e) {
				jda = null;
				new LoggerSingleton().getExceptionLogger().warn("JDASingleton: Discord Login failed. ", e);
			}
		}
		return jda;
	}
}
