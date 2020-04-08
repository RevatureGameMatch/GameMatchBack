package com.revature.g2g.services.jda;

import java.util.List;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PropertiesHelper;
import com.revature.g2g.services.jda.listeners.GuildVoiceEventListener;
import com.revature.g2g.services.jda.listeners.MessageListener;
import com.revature.g2g.services.jda.listeners.ReadyListener;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JDASingleton {
	@Autowired
	private LoggerSingleton loggerSingleton;
	@Autowired
	private PropertiesHelper propertiesHelper;
	private JDA jda;
	private Guild guild;
	private boolean ready = false;
	public JDASingleton() {
	}
	public JDA getJda() {
		if(jda == null) {
			try {
				ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
				JDABuilder builder = JDABuilder.createDefault(propertiesHelper.getPropValues().getProperty("discordKey"));
				builder.setActivity(Activity.watching("Users for new commands."));
				builder.addEventListeners(ac.getBean(GuildVoiceEventListener.class));
				builder.addEventListeners(ac.getBean(MessageListener.class));
				builder.addEventListeners(ac.getBean(ReadyListener.class));
				jda = builder.build();
			} catch (LoginException e) {
				jda = null;
				loggerSingleton.getExceptionLogger().warn("JDASingleton: Discord Login failed. ", e);
			}
		}
		return jda;
	}
	public Guild getGuild() {
		if(!ready) {
			loggerSingleton.getExceptionLogger().warn("JDA not ready when guild called");
			return null;
		}
		if(guild == null) {
			List<Guild> guilds = jda.getGuilds();
			for(Guild guildFound : guilds) {
				guild = guildFound;
			}
		}
		return guild;
	}
	public boolean getReady() {
		return ready;
	}
	public void setReady(boolean readyIn) {
		ready = readyIn;
	}
}
