package com.revature.g2g.services.helpers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Service;

import net.dv8tion.jda.api.Permission;

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
		return permissions;
	}
	public Collection<Permission> getRoleVoiceBans(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
	public Collection<Permission> getRoleTextPermissions(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
	public Collection<Permission> getRoleTextBans(){
		ArrayList<Permission> permissions = new ArrayList<>();
		return permissions;
	}
}
