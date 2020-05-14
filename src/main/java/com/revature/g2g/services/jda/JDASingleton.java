package com.revature.g2g.services.jda;

import java.util.Properties;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.helpers.PropertiesSingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class JDASingleton {
	private JDA jda;
	public JDA getJda() {
		if(jda == null) {
			try {
				Properties properties = PropertiesSingleton.getPropValues();
				if(properties == null)return null;
				String discordKey = properties.getProperty("discordKey");
				JDABuilder builder = JDABuilder.createDefault(discordKey);
				builder.setActivity(Activity.watching("Users for new commands."));
				jda = builder.build();
				new LoggerSingleton().getDiscordLog().trace("JDA building");
				jda.awaitReady();
				new LoggerSingleton().getDiscordLog().trace("JDA done building");
			} catch (LoginException e) {
				jda = null;
				new LoggerSingleton().getExceptionLogger().warn("JDASingleton: Discord Login failed. ", e);
			} catch (InterruptedException e) {
				new LoggerSingleton().getExceptionLogger().warn("JDASingleton: Thread sleep interupted");
				Thread.currentThread().interrupt();
			}
		}
		return jda;
	}
}
