package com.revature.g2g.services.helpers;

import org.springframework.stereotype.Service;

@Service
public class DiscordHelper {
	static boolean adminCheck(String qualifiedUsername) {
		boolean result = false;
		if((qualifiedUsername.equals("ProNobis#7047")||qualifiedUsername.equals("kfilio#6124")||qualifiedUsername.equals("shotofthewritten#5186"))) {
			result = true;
		}
		return result;
	}
}
