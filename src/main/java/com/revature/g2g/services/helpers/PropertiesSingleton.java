package com.revature.g2g.services.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.core.config.plugins.util.ResolverUtil.Test;

public class PropertiesSingleton {
	private static Properties properties;
	private static LoggerSingleton loggerSingleton;
	private PropertiesSingleton() {
	}
	public static Properties getPropValues(){
		if(loggerSingleton == null) {
			loggerSingleton = new LoggerSingleton();
		}
		if(properties == null) {
			Properties newProperties = new Properties();
			String file = "/config.properties";
//			String file = "./src/main/resources/config.properties";
//			try (InputStream inputStream = new FileInputStream(file)){
			try (InputStream inputStream = Test.class.getResourceAsStream(file)){
				newProperties.load(inputStream);
				properties = newProperties;
			} catch (FileNotFoundException e) {
				String relPath = new File(".").getAbsolutePath();
				loggerSingleton.getExceptionLogger().warn("properties file \"" + relPath.substring(0, relPath.length() - 1) + file + "\" not found",e);
			} catch (IOException e) {
				loggerSingleton.getExceptionLogger().warn("Properties retrieval failed: ", e);
			}
		}
		return properties;
	}
}
