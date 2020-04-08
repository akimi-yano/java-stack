package com.example.code;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

	@RequestMapping("/")
	public String main() {
		return "index.jsp";
	}
	
	@RequestMapping(value="/test", method=RequestMethod.POST)
	public String test(@RequestParam(value="code") String code, RedirectAttributes redirectAttributes) {
    if (code.equals("bushido")) {
    	return "redirect:/code";
    }
    else {
    	redirectAttributes.addFlashAttribute("error", "Train harder");
    	return "redirect:/";
    }
    
    
  }
	@RequestMapping("/code")
	public String secret() {
		return "index2.jsp";
	}
	
}
