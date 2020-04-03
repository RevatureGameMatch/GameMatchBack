package com.revature.g2g.services.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSingleton {
	private static Logger exceptionLog;
	private static Logger businessLog;
	private static Logger accessDeniedLog;
	private static Logger discordLog;
	private LoggerSingleton() {
	}
	public static Logger getExceptionLogger() {
		if (exceptionLog == null) {
			exceptionLog =  LogManager.getLogger("exceptionLogger");
		}
		return exceptionLog;
	}
	public static Logger getBusinessLog() {
		if(businessLog == null) {
			businessLog = LogManager.getLogger("businessLogger");
		}
		return businessLog;
	}
	public static Logger getAccessLog() {
		if(accessDeniedLog == null) {
			accessDeniedLog = LogManager.getLogger("accessLogger");
		}
		return accessDeniedLog;
	}
	public static Logger getDiscordLog() {
		if(discordLog == null) {
			discordLog = LogManager.getLogger("discordLogger");
		}
		return discordLog;
	}
}
