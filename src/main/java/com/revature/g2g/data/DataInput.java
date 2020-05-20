package com.revature.g2g.data;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

// TODO: move this to a more appropriate folder
public class DataInput {
	public static String sanitize(String source) {
		String returnThis = Jsoup.clean(source, Whitelist.none());
		
		return returnThis;
	}
}
