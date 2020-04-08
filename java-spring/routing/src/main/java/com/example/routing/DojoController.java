package com.example.routing;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/m")
public class DojoController {

//	Create another controller named 'DojoController'. 
//	For the routes below, use the @PathVariable annotation.
	
//	Have an http GET request to 'http://localhost:8080/dojo' 
//	display a text that says 'The dojo is awesome!'.
	
//	Have an http GET request to 'http://localhost:8080/burbank-dojo/' 
//	display a text that says 'Burbank Dojo is located in Southern California'.
	
//	Have an http GET request to 'http://localhost:8080/san-jose/' 
//	display a text that says 'SJ dojo is the headquarters'.
	
	@RequestMapping("/{keyword}")
	public String keyword(@PathVariable("keyword") String keyword) {
	
		if (keyword.equals("dojo")) {
			return "The dojo is awesome!";
		}
		else if (keyword.equals("burbank-dojo")) {
			return "Burbank Dojo is located in Southern California";
		}
		else if (keyword.equals("san-jose")) {
			return "SJ dojo is the headquarters";
		}
		else {
			return "no page found";
		}

	}
	
	
}
