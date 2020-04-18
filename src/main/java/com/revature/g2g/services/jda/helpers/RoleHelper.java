package com.revature.g2g.services.jda.helpers;


import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.restaction.RoleAction;

public class RoleHelper {
	private RoleHelper() {
	}
	public static Role insert(Guild guild, String name) {
		RoleAction roleAction = guild.createRole();
		roleAction.setName(name);
		return roleAction.complete();
	}
	public static void delete(Role role) {
		role.delete().complete();
	}
}
