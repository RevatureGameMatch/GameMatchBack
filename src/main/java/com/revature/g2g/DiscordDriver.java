package com.revature.g2g;

import com.revature.g2g.data.DummyDataDriver;
import com.revature.g2g.services.jda.JDASingleton;

public class DiscordDriver {
	public static void main(String[] args) {
		JDASingleton.getJda();
		DummyDataDriver.generate();
	}
}
