package com.example.counter;

//Tasks:
//● Create and display a counter on http://your_server/counter
//
//● Increment the counter on each visit to http://your_server/
//
//● Counter must start at zero
//
//● No errors regardless of which page is visited first
//
//Optional Challenges:
//● Create a third page that will increment the counter by 2
//
//● Create a reset button to set the counter back to zero

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/your_server")
public class MainController { 

	@RequestMapping("/counter")
    public String current(Model model, HttpSession session){
        Integer count = (Integer) session.getAttribute("count");
		model.addAttribute("counter", (count != null) ? count : 0);
        return "index.jsp";
    }
    
	@RequestMapping("/")
	public String increment(HttpSession session) {
	    // Freshly created.
		if (session.getAttribute("count") == null) {
		    session.setAttribute("count", 1);
		    return "index2.jsp";
		
		// Already created.
		} else {
			System.out.println(session.getAttribute("count"));
			session.setAttribute("count", ((Integer) session.getAttribute("count")) + 1);
	        return "index2.jsp";
		}
	}
	
	@RequestMapping("/double")
	public String doubleIncrement(HttpSession session) {
	    // Freshly created.
		if (session.getAttribute("count") == null) {
		    session.setAttribute("count", 2);
		    return "index3.jsp";
		
		// Already created.
		} else {
			System.out.println(session.getAttribute("count"));
			session.setAttribute("count", ((Integer) session.getAttribute("count")) + 2);
	        return "index3.jsp";
		}
	}
	
	@RequestMapping("/reset")
	public String reset(HttpSession session) {
		session.removeAttribute("count");
		return "redirect:/your_server/counter";
	}
	
	

}
