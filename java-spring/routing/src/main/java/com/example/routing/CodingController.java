package com.example.routing;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Create a controller named 'CodingController'. 

@RestController
@RequestMapping("/coding")
public class CodingController {

//	For the first 3 routes, use the @RequestMapping annotation.
	
//	Have an http GET request to 'http://localhost:8080/coding' 
//	display a text that says 'Hello Coding Dojo!'.
	
//	Have an http GET request to 'http://localhost:8080/coding/python' 
//	display a text that says 'Python/Django was awesome!'.
	
//	Have an http GET request to 'http://localhost:8080/coding/java' 
//	display a text that says 'Java/Spring is better!'.
	
	
	@RequestMapping("/")
	public String hello() {
		return "Hello Coding Dojo!";
	}
	
	@RequestMapping("/python")
	public String hello2() {
		return "Python/Dango was awesome!";
	}
	
	@RequestMapping("/java")
	public String hello3() {
		return "Java/Spring is better!";
	}
	
}
