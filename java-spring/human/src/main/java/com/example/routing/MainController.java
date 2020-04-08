package com.example.routing;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/your_server")
public class MainController {
	@RequestMapping("/")
	public String greetPage1(){

		return "Hello human ! " +
		
		"Welcome to SpringBoot!";
	}

	@RequestMapping("/{first_name}/{last_name}")
	public String greetPage2(
			@PathVariable("first_name") String firstName, 
			@PathVariable("last_name") String lastName) {
		
		return "Hello " + firstName + " " + lastName + "!!!! " +
		
		"Welcome to SpringBoot!";
	}
	
}
