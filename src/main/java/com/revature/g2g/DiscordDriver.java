package com.revature.g2g;

import java.util.List;

import com.revature.g2g.data.DummyDataDriver;
import com.revature.g2g.services.helpers.LoggerSingleton;
import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;

public class DiscordDriver {
	public static void main(String[] args) {
		JDA jda = JDASingleton.getJda();
		DummyDataDriver.generate();
	}
}
