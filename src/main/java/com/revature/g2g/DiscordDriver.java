package com.revature.g2g;

import com.revature.g2g.data.DummyDataDriver;
import com.revature.g2g.services.jda.JDASingleton;

import net.dv8tion.jda.api.entities.Guild;

public class DiscordDriver {
	public static void main(String[] args) {
		JDASingleton.getJda();
		DummyDataDriver.generate();
	}
}
