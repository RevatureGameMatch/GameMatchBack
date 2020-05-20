package com.revature.g2g.services.helpers;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class SanitizerHelper {

	private SanitizerHelper() {
	}

	public static String sanitize(String source) {
		if (source == null) return null;
		String result = Jsoup.clean(source, Whitelist.none());//prevent HTML injection
		result = result.replace(";", "_");//prevent SQL injection
		
		return result;
	}
}
