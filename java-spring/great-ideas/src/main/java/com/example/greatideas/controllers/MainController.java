package com.example.greatideas.controllers;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.greatideas.models.Idea;
import com.example.greatideas.models.User;
import com.example.greatideas.repos.IdeaRepo;
import com.example.greatideas.repos.UserRepo;
import com.example.greatideas.servs.IdeaServ;
import com.example.greatideas.servs.UserServ;
import com.example.greatideas.validators.UserValidator;

@Controller
public class MainController {
	private final UserRepo urepo;
	private final UserServ userv;
	private final IdeaRepo irepo;
	private final IdeaServ iserv;
	private final UserValidator uvalid;
	public MainController(
			UserRepo urepo,
			UserServ userv,
			IdeaRepo irepo,
			IdeaServ iserv,
			UserValidator uvalid
			) {
		this.urepo = urepo;
		this.userv = userv;
		this.irepo = irepo;
		this.iserv = iserv;
		this.uvalid = uvalid;
	}

	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("user", new User());
		return "index.jsp";
	}
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		uvalid.validate(user, result);
		if(result.hasErrors()) {
			return "index.jsp";
		} else {
			User u = userv.registerUser(user);
			session.setAttribute("userid", u.getId());
			return "redirect:/dash";
		}
	}
	
	@PostMapping("/login")
	public String loginUser(@RequestParam("email") String email,@RequestParam("password") String password, Model model, HttpSession session) {
		if(userv.authenticateUser(email, password)) {
			User u = urepo.findByEmail(email);
			session.setAttribute("userid", u.getId());
			return "redirect:/dash";
		} else {
			// user model attribute  is used in registration form, so we need to set a dummy one here
			// (this is because registration and  login are  on the same page
			model.addAttribute("user", new User());
			model.addAttribute("error", "Invalid login");
			return "index.jsp";
		}  
    }
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	@GetMapping("/dash")
	public String dashboard(@RequestParam("order") Optional<String> maybeOrderType,
			HttpSession session, Model model) {
		Long userid = (Long) session.getAttribute("userid");
		User u = userv.findOne(userid);

		model.addAttribute("user", u);
//		List<Idea> mylist = irepo.findAll(Sort.by(Sort.Direction.ASC, "likedUsers"));
		List<Idea> mylist = irepo.findAll();
		// lambda expression to sort ideas by likes
		if (maybeOrderType.isPresent()) {
			String orderType = maybeOrderType.get();
			if (orderType.equals("likesAscending")) {
				mylist.sort(
						(Idea a, Idea b)->a.getLiked_users().size() - b.getLiked_users().size()
						);
				}
			else if (orderType.equals("likesDescending")) {
				mylist.sort(
						(Idea a, Idea b)->b.getLiked_users().size() - a.getLiked_users().size()
						);
				}
		}
		model.addAttribute("ideas",mylist);
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String newIdea(Model model) {
		model.addAttribute("idea", new Idea());
		return "new.jsp";
	}
	
	@PostMapping("/new")
	public String createIdea(HttpSession session, @Valid @ModelAttribute("idea") Idea idea, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			User u = userv.findOne((Long) session.getAttribute("userid"));
			idea.setCreator(u);
			
			//make a new list to add the creator to like the idea by default
			ArrayList<User> newList = new ArrayList<User>();
			newList.add(u);
			idea.setLiked_users(newList);
			irepo.save(idea);
			return "redirect:/dash";
		}
	}
	@GetMapping("/show/{idea_id}")
	public String showPage(Model model, @PathVariable("idea_id") Long ideaId) {
		Idea i = iserv.findOne(ideaId);
		model.addAttribute("idea", i);
		return "show.jsp";
	}
	
	@GetMapping("/edit/{idea_id}")
	public String editPage(Model model, @PathVariable("idea_id") Long ideaId) {
		// populate  the  value beforehand
		Idea i = iserv.findOne(ideaId);
		model.addAttribute("idea", i);
		return "edit.jsp";
	}
	
	@PostMapping("/edit/{idea_id}")
	public String doEdit(Model model, @PathVariable("idea_id") Long ideaId, @Valid @ModelAttribute("idea") Idea idea, BindingResult result) {
		if(result.hasErrors()) {
			idea.setId(ideaId);
			return "edit.jsp";
			
		} else {
			// form data doesn't include the creator id, so get it and set it
			User ideaCreator = iserv.findOne(ideaId).getCreator();
			idea.setCreator(ideaCreator);
			// get the data about  the liker from the database and set it 
			List <User> ideaLikers = iserv.findOne(ideaId).getLiked_users();
			idea.setLiked_users(ideaLikers);
			idea.setId(ideaId);
			irepo.save(idea);
			return "redirect:/dash";
		}
	}
	
	
	@GetMapping("/delete/{idea_id}")
	public String deleteIdea(@PathVariable("idea_id") Long ideaId, HttpSession session) {
		Long uid = (Long) session.getAttribute("userid");
		Idea i = iserv.findOne(ideaId);
		
		boolean idsEqual = i.getCreator() != null
				&& i.getCreator().getId().longValue() == uid.longValue();
		if(!idsEqual) {
			System.out.println("idsEqual: " + idsEqual + "; idea creator=" + i.getCreator().getId() + ", user id=" + uid);
			return "redirect:/dash";
		} else {
			irepo.deleteById(ideaId);
			return "redirect:/dash";
		}
	}
	
	@GetMapping("/like/{idea_id}")
	public String likeIdea(@PathVariable("idea_id") Long ideaId, HttpSession session) {
//		logic to like
		User u = userv.findOne((Long) session.getAttribute("userid"));
		Idea i = iserv.findOne(ideaId);
		iserv.like(i, u);
		return "redirect:/dash";
	}
	
	@GetMapping("/unlike/{idea_id}")
	public String unlikeIdea(@PathVariable("idea_id") Long ideaId, HttpSession session) {
//		logic to unlike
		User u = userv.findOne((Long) session.getAttribute("userid"));
		Idea i = iserv.findOne(ideaId);
		iserv.unlike(i, u);
		return "redirect:/dash";
	}

	
}



	
	

	
