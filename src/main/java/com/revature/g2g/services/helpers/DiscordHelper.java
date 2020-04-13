package com.revature.g2g.services.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.VoiceChannel;

@Service
public class DiscordHelper {
	public boolean adminCheck(String qualifiedUsername) {
		boolean result = false;
		if((qualifiedUsername.equals("ProNobis#7047")||qualifiedUsername.equals("kfilio#6124")||qualifiedUsername.equals("shotofthewritten#5186"))) {
			result = true;
		}
		return result;
	}
	public Collection<Permission> getRoleVoicePermissions(){
		ArrayList<Permission> permissions = new ArrayList<>();
		permissions.add(Permission.VIEW_CHANNEL);
		permissions.add(Permission.VOICE_CONNECT);
		permissions.add(Permission.VOICE_SPEAK);
		permissions.add(Permission.VOICE_STREAM);
		permissions.add(Permission.VOICE_USE_VAD);
		return permissions;
	}
	public Collection<Permission> getRoleVoiceBans(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
	public Collection<Permission> getRoleTextPermissions(){
		ArrayList<Permission> permissions = new ArrayList<>();
		permissions.add(Permission.MESSAGE_READ);
		permissions.add(Permission.MESSAGE_WRITE);
		permissions.add(Permission.MESSAGE_HISTORY);
		permissions.add(Permission.MESSAGE_ADD_REACTION);
		return permissions;
	}
	public Collection<Permission> getRoleTextBans(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
	public Role getDiscordBot() {
		JDA jda = JDASingleton.getJda();
		return jda.getRoleById(695341021277454426L);
	}
	public Collection<Permission> getDiscordBotVoicePermission(){
		ArrayList<Permission> permissions = new ArrayList<>();
		permissions.add(Permission.MANAGE_CHANNEL);
		return permissions;
	}
	public Collection<Permission> getDiscordBotTextPermission(){
		ArrayList<Permission> permissions = new ArrayList<>();
		permissions.add(Permission.MANAGE_CHANNEL);
		return permissions;
	}
	public Collection<Permission> getEmptyPermission(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
	public VoiceChannel getGeneralVoice() {
		JDA jda = JDASingleton.getJda();
		return jda.getVoiceChannelById(698632671525601421L);
	}
}
