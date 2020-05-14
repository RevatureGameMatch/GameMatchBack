package com.revature.g2g.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.revature.g2g.api.templates.MessageTemplate;

@CrossOrigin
@RequestMapping("Discord")
@RestController
public class DiscordController {
	@GetMapping("/Hello")
	public ResponseEntity<MessageTemplate> helloDiscord(){
		MessageTemplate message = new MessageTemplate("Hello Discord");
		System.out.println(message.toString());
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
	@GetMapping("/Authenticate")
	public ResponseEntity<MessageTemplate> authenticate(@RequestParam(name = "token") String token){
		MessageTemplate message = new MessageTemplate(token);
		System.out.println(message.toString());
		return ResponseEntity.status(HttpStatus.OK).body(message);
	}
}
