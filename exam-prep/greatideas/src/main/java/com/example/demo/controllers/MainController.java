package com.example.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Idea;
import com.example.demo.models.User;
import com.example.demo.repos.IdeaRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.servs.UserServ;
import com.example.demo.validators.UserValidator;

@Controller
public class MainController {
	private final UserRepo urepo;
	private final UserServ userv;
	private final IdeaRepo irepo;
	private final UserValidator userValidator;
	public MainController(UserRepo urepo, UserServ userv, IdeaRepo irepo, UserValidator userValidator) {
		this.urepo = urepo;
		this.userv = userv;
		this.irepo = irepo;
		this.userValidator = userValidator;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index.jsp";
	}
	
	@PostMapping("/registration")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
        userValidator.validate(user, result);
        // dont use duplicate emails to register
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			User u = userv.registerUser(user);
			session.setAttribute("userid", u.getId());
			return "redirect:/ideas";
		}
    }
	
	@PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(userv.authenticateUser(email, password)) {
			User u = urepo.findByEmail(email);
			session.setAttribute("userid", u.getId());
			return "redirect:/ideas";
		} else {
			model.addAttribute("user", new User());
			model.addAttribute("error", "Invalid login");
			return "index.jsp";
		}
        
    }
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
//	--------------------------
	@GetMapping("/ideas")
	public String table(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("userid");
		model.addAttribute("me", urepo.findById(id).orElse(null));
		model.addAttribute("ideas", irepo.findAll());
		return "ideas.jsp";
	}
	
	@GetMapping("/ideas/new")
	public String showIdeaForm(Model model) {
		model.addAttribute("idea", new Idea());
		return "new.jsp";
	}
	
	@PostMapping("/ideas/new")
	public String doIdeaForm(HttpSession session, @Valid @ModelAttribute("idea") Idea idea, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			Long sessionId = (Long) session.getAttribute("userid");
			User u = urepo.findById(sessionId).orElse(null);
			idea.setCreator(u);
			irepo.save(idea);
			return "redirect:/ideas";
		}
	}
	
	@GetMapping("/like/{idea_id}")
	public String likeIdea(HttpSession session, @PathVariable("idea_id") Long id) {
		Long sessionId = (Long) session.getAttribute("userid");
		User u = urepo.findById(sessionId).orElse(null);
		Idea i = irepo.findById(id).orElse(null);
		i.getLikers().add(u);
		irepo.save(i);
		return "redirect:/ideas";
	}
	@GetMapping("/unlike/{idea_id}")
	public String unlikeIdea(HttpSession session, @PathVariable("idea_id") Long id) {
		Long sessionId = (Long) session.getAttribute("userid");
		User u = urepo.findById(sessionId).orElse(null);
		Idea i = irepo.findById(id).orElse(null);
		i.getLikers().remove(u);
		irepo.save(i);
		return "redirect:/ideas";
	}
	
	@GetMapping("/ideas/{idea_id}")
	public String detailsIdea(Model model, @PathVariable("idea_id") Long id) {
		model.addAttribute("idea", irepo.findById(id).orElse(null));
		return "details.jsp";
	}
	
	@GetMapping("/ideas/{idea_id}/edit")
	public String showIdeaEdit(HttpSession session, Model model, @PathVariable("idea_id") Long id) {
		Idea i = irepo.findById(id).orElse(null);
		Long userid = (Long) session.getAttribute("userid");
		if(i.getCreator().getId().equals(userid) == false) {
			return "redirect:/ideas";
		}
		model.addAttribute("idea", irepo.findById(id).orElse(null));
		return "edit.jsp";
	}
	
	@PostMapping("/ideas/{idea_id}/edit")
	public String doIdeaEdit(@PathVariable("idea_id") Long id, @Valid @ModelAttribute("idea") Idea idea, BindingResult result ) {
		if(result.hasErrors()) {
			idea.setId(id);
			return "edit.jsp";
		} else {
			Idea original = irepo.findById(id).orElse(null);
			original.setName(idea.getName());
			irepo.save(original);
			return "redirect:/ideas";
		}
	}
	
	@GetMapping("/delete/{idea_id}")
	public String deleteIdea(@PathVariable("idea_id") Long id) {
		irepo.deleteById(id);
		return "redirect:/ideas";
	}
	
	@GetMapping("/sort/low")
	public String sortLow(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("userid");
		model.addAttribute("me", urepo.findById(id).orElse(null));
		List<Idea> list = irepo.findAll();
		ArrayList<Idea> customList = new ArrayList<Idea>();
		while(list.size() != 0) {
			int min = list.get(0).getLikers().size();
			int idx = 0;
			for(int i = 0; i < list.size(); i++) {
				if(min > list.get(i).getLikers().size()) {
					min = list.get(i).getLikers().size();
					idx = i;
				}
			}
			customList.add(list.remove(idx));
		}
		model.addAttribute("ideas", customList);
		return "ideas.jsp";
	}
	@GetMapping("/sort/high")
	public String sortHigh(HttpSession session, Model model) {
		Long id = (Long) session.getAttribute("userid");
		model.addAttribute("me", urepo.findById(id).orElse(null));
		List<Idea> list = irepo.findAll();
		ArrayList<Idea> customList = new ArrayList<Idea>();
		while(list.size() != 0) {
			int max = list.get(0).getLikers().size();
			int idx = 0;
			for(int i = 0; i < list.size(); i++) {
				if(max < list.get(i).getLikers().size()) {
					max = list.get(i).getLikers().size();
					idx = i;
				}
			}
			customList.add(list.remove(idx));
		}
		model.addAttribute("ideas", customList);
		return "ideas.jsp";
	}
}
