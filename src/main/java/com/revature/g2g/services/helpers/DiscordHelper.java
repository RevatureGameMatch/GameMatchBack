package com.revature.g2g.services.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;

@Service
public class DiscordHelper {
	/**
	 * Checks a string against the discord usernames of the sprint 1 team. This was meant
	 * as a temporary security measure until OAuth with discord api could be implemented.
	 * @param qualifiedUsername
	 * @return boolean
	 */
	public boolean adminCheck(String qualifiedUsername) {
		boolean result = false;
		if((qualifiedUsername.equals("ProNobis#7047")||qualifiedUsername.equals("kfilio#6124")||qualifiedUsername.equals("shotofthewritten#5186"))) {
			result = true;
		}
		return result;
	}
	/**
	 * Gets Collection of Permission enums needed to apply security changes to new roles for new voice channels
	 * @return Collection<Permission> permissions
	 */
	public Collection<Permission> getRoleVoicePermissions(){
		ArrayList<Permission> permissions = new ArrayList<>();
		permissions.add(Permission.VIEW_CHANNEL);
		permissions.add(Permission.VOICE_CONNECT);
		permissions.add(Permission.VOICE_SPEAK);
		permissions.add(Permission.VOICE_STREAM);
		permissions.add(Permission.VOICE_USE_VAD);
		return permissions;
	}
	/**
	 * Gets Collection of Permission enums needed to restrict security changes to new roles for new voice channels
	 * @return Collection<Permission> permissions
	 */
	public Collection<Permission> getRoleVoiceBans(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
	/**
	 * Gets Collection of Permission enums needed to apply security changes to new roles for new text channels
	 * @return Collection<Permission> permissions
	 */
	public Collection<Permission> getRoleTextPermissions(){
		ArrayList<Permission> permissions = new ArrayList<>();
		permissions.add(Permission.MESSAGE_READ);
		permissions.add(Permission.MESSAGE_WRITE);
		permissions.add(Permission.MESSAGE_HISTORY);
		permissions.add(Permission.MESSAGE_ADD_REACTION);
		return permissions;
	}
	/**
	 * Gets Collection of Permission enums needed to restrict security changes to new roles for new text channels
	 * @return Collection<Permission> permissions
	 */
	public Collection<Permission> getRoleTextBans(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
	/**
	 * Gets the role for the discord bot by hard coded id.
	 * @return Role discordBot
	 */
	public Role getDiscordBot() {
		JDA jda = JDASingleton.getJda();
		return jda.getRoleById(695341021277454426L);
	}
}
