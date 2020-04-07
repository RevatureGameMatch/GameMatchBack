package com.revature.g2g.services.helpers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class LoggerSingleton {
	private Logger exceptionLog;
	private Logger businessLog;
	private Logger accessDeniedLog;
	private Logger discordLog;
	public LoggerSingleton() {
	}
	public LoggerSingleton(Logger exceptionLog, Logger businessLog, Logger accessDeniedLog, Logger discordLog) {
		super();
		this.exceptionLog = exceptionLog;
		this.businessLog = businessLog;
		this.accessDeniedLog = accessDeniedLog;
		this.discordLog = discordLog;
	}
	public Logger getExceptionLogger() {
		if (exceptionLog == null) {
			exceptionLog =  LogManager.getLogger("exceptionLogger");
		}
		return exceptionLog;
	}
	public Logger getBusinessLog() {
		if(businessLog == null) {
			businessLog = LogManager.getLogger("businessLogger");
		}
		return businessLog;
	}
	public Logger getAccessLog() {
		if(accessDeniedLog == null) {
			accessDeniedLog = LogManager.getLogger("accessLogger");
		}
		return accessDeniedLog;
	}
	public Logger getDiscordLog() {
		if(discordLog == null) {
			discordLog = LogManager.getLogger("discordLogger");
		}
		return discordLog;
	}
}
