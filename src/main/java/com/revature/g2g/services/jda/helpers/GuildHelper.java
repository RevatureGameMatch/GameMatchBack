package com.revature.g2g.services.jda.helpers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

@Service
public class GuildHelper {
	private Guild guild;
	@Autowired
	private JDASingleton jdaSingleton;

	public Guild getGuild() {
		if(guild == null) {
			JDA jda = jdaSingleton.getJda();
			if(jda != null) {
				List<Guild> guilds = jda.getGuilds();
				for(Guild guildFound : guilds) {
					guild = guildFound;
				}
			}
		}
		return guild;
	}
}
