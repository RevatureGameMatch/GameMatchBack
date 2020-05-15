package com.revature.g2g.services.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class EnvironmentService {
	@Autowired
	private Environment environment;
	public boolean isDev() {
		boolean dev = false;
		String[] environments = environment.getActiveProfiles();
		for (String environmentString : environments) {
			if(environmentString.equals("dev")) {
				dev = true;
			}
		}
		return dev;
	}
}
