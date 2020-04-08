package com.revature.g2g.services.jda.listeners;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.revature.g2g.services.helpers.LoggerSingleton;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class ReadyListener  implements EventListener{
	@Override
	public void onEvent(GenericEvent event) {
		if(event instanceof ReadyEvent) {
			new LoggerSingleton().getExceptionLogger().trace("Discord API is ready!");
		}
	}
}
