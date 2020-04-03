package com.revature.g2g.services.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesHelper {
	private static Properties properties;
	private PropertiesHelper() {
	}
	public static Properties getPropValues(){
		if(properties == null) {
			Properties properties = new Properties();
			String file = "./src/main/resources/config.properties";
			try (InputStream inputStream = new FileInputStream(file)){
				properties.load(inputStream);
				PropertiesHelper.properties = properties;
			} catch (FileNotFoundException e) {
				String relPath = new File(".").getAbsolutePath();
				LoggerSingleton.getExceptionLogger().warn("properties file \"" + relPath.substring(0, relPath.length() - 1) + file + "\" not found",e);
			} catch (IOException e) {
				LoggerSingleton.getExceptionLogger().warn("Properties retrieval failed: ", e);
			}
		}
		return PropertiesHelper.properties;
	}
}
