package com.revature.g2g.services.helpers;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

// TODO: move this to a more appropriate folder
public class SanitizerHelper {
	public static String sanitize(String source) {
		String result = Jsoup.clean(source, Whitelist.none());
		result.replace(";", "_");
		
		return result;
	}
}
