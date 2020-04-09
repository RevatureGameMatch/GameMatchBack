package com.revature.g2g.services.jda.helpers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.g2g.exceptions.NullGuildException;
import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

@Service
public class GuildHelper {
	private Guild guild;

	public Guild getGuild() {
		if(guild == null) {
			JDA jda = JDASingleton.getJda();
			List<Guild> guilds = jda.getGuilds();
			for(Guild guildFound : guilds) {
				guild = guildFound;
			}
		}
		if(guild == null) {
			throw new NullGuildException("Null guild called from GuildHelper");
		}
		return guild;
	}
}
