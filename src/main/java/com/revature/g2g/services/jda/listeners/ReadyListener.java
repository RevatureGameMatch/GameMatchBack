package com.revature.g2g.services.jda.listeners;

import com.revature.g2g.services.helpers.LoggerSingleton;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyListener  implements EventListener{
	@Override
	public void onEvent(GenericEvent event) {
		if(event instanceof ReadyEvent) {
			LoggerSingleton.getExceptionLogger().trace("Discord API is ready!");
		}
	}
}
