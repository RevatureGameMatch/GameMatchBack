package com.revature.g2g.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.MessageTemplate;

@CrossOrigin
@RestController
public class HelloController {
	@GetMapping("/hello")
	public ResponseEntity<MessageTemplate> helloWorld(){
		MessageTemplate message = new MessageTemplate("Hello World");
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}