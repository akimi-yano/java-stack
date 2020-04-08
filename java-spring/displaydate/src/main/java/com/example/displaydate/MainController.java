package com.example.displaydate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	@RequestMapping("/")
	public String index(Model model) {
		return "index.jsp";
	}
	@RequestMapping("/date")
	public String index2(Model model) {
		String date = (new java.text.SimpleDateFormat("EEEEEEEE, 'the' dd 'of' MMMMMMMMMM, y")).format(new java.util.Date());
		model.addAttribute("date", date);
		return "index2.jsp";
	}
	@RequestMapping("/time")
	public String index3(Model model) {
		String time = (new java.text.SimpleDateFormat("h:m a")).format(new java.util.Date());
		model.addAttribute("time", time);
		return "index3.jsp";
	}

	
	
}
