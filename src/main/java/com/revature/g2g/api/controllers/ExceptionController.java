package com.revature.g2g.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="error")
public class ExceptionController {
	@GetMapping
	public void sendException(){
		throw new RuntimeException("let's break this!");
	}
}
