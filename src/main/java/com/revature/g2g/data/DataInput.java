package com.revature.g2g.data;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class DataInput {
	public static String sanitize(String source) {
		String returnThis = Jsoup.clean(source, Whitelist.none());
		
		return returnThis;
	}
}
