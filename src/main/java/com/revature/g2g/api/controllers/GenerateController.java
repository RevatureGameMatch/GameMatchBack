package com.revature.g2g.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.MessageTemplate;
import com.revature.g2g.data.DummyDataDriver;
import com.revature.g2g.services.business.EnvironmentService;
import com.revature.g2g.services.helpers.LoggerSingleton;

@CrossOrigin
@RestController
@RequestMapping(value="generate")
public class GenerateController {
	private EnvironmentService environmentService;
	private DummyDataDriver dummyDataDriver;
	private LoggerSingleton loggerSingleton;
	@Autowired
	public void setEnvironmentService(EnvironmentService environmentService) {
		this.environmentService = environmentService;
	}
	@Autowired
	public void setDummyDataDriver(DummyDataDriver dummyDataDriver) {
		this.dummyDataDriver = dummyDataDriver;
	}
	@Autowired
	public void setLoggerSingleton(LoggerSingleton loggerSingleton) {
		this.loggerSingleton = loggerSingleton;
	}
	
	@GetMapping
	public ResponseEntity<MessageTemplate> triggerGeneration(){
		if(environmentService.isDev()) {
			String message = "Generation Triggered by api hook.";
			MessageTemplate messageTemplate = new MessageTemplate(message);
			loggerSingleton.getAccessLog().trace(message);
			dummyDataDriver.generate();
			return ResponseEntity.ok().body(messageTemplate);
		}else {
			MessageTemplate message = new MessageTemplate("Only available on dev environments.");
			loggerSingleton.getAccessLog().warn("GenerateController: attempt to run generation from non dev environment.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
		}
	}
}
