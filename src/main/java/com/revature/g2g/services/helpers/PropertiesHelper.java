package com.revature.g2g.services.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PropertiesHelper {
	private Properties properties;
	@Autowired
	private LoggerSingleton loggerSingleton;
	public PropertiesHelper() {
		super();
	}
	public Properties getPropValues(){
		if(properties == null) {
			Properties newProperties = new Properties();
			String file = "./src/main/resources/config.properties";
			try (InputStream inputStream = new FileInputStream(file)){
				newProperties.load(inputStream);
				this.properties = newProperties;
			} catch (FileNotFoundException e) {
				String relPath = new File(".").getAbsolutePath();
				loggerSingleton.getExceptionLogger().warn("properties file \"" + relPath.substring(0, relPath.length() - 1) + file + "\" not found",e);
			} catch (IOException e) {
				loggerSingleton.getExceptionLogger().warn("Properties retrieval failed: ", e);
			}
		}
		return this.properties;
	}
}
