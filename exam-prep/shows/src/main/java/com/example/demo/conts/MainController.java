package com.example.demo.conts;

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

import com.example.demo.models.Rating;
import com.example.demo.models.Show;
import com.example.demo.models.User;
import com.example.demo.repos.RatingRepo;
import com.example.demo.repos.ShowRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.servs.UserServ;
import com.example.demo.validators.UserValidator;

@Controller
public class MainController {
	private final UserRepo urepo;
	private final UserServ userv;
	private final UserValidator userValidator;
	private final ShowRepo srepo;
	private final RatingRepo rrepo;
	public MainController(
			UserRepo urepo,
			UserServ userv,
			UserValidator userValidator,
			ShowRepo srepo,
			RatingRepo rrepo
			) {
		this.urepo = urepo;
		this.userv = userv;
		this.userValidator = userValidator;
		this.srepo = srepo;
		this.rrepo = rrepo;
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
			return "redirect:/shows";
		}
    }
	
	@PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(userv.authenticateUser(email, password)) {
			User u = urepo.findByEmail(email);
			session.setAttribute("userid", u.getId());
			return "redirect:/shows";
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
//	------
	
	@GetMapping("/shows")
	public String dashboard(HttpSession session, Model model) {
		Long userid = (Long) session.getAttribute("userid");
		User u = urepo.findById(userid).orElse(null);
		model.addAttribute("me", u);
		List<Show> original = srepo.findAll();
		for(int i = 0; i < original.size(); i++) {
			double counter = 0.0;
			for(int j = 0; j < original.get(i).getRatings().size(); j++) {
				counter += original.get(i).getRatings().get(j).getRating();
			}
			if(original.get(i).getRatings().size() == 0) {
				original.get(i).setAverage(0);
			} else {
				original.get(i).setAverage(counter / original.get(i).getRatings().size());
			}
		}
		model.addAttribute("shows", original);
		return "dashboard.jsp";
	}
	
	@GetMapping("/shows/new")
	public String showForm(Model model) {
		model.addAttribute("show", new Show());
		return "new.jsp";
	}
	@PostMapping("/shows/new")
	public String doForm(Model model, HttpSession session, @Valid @ModelAttribute("show") Show show, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			List<Show> list = srepo.findAll();
			boolean flag = false;
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getTitle().equals(show.getTitle())) {
					flag = true;
					model.addAttribute("titleUnique", "Title is not unique");
				}
				if(list.get(i).getTitle().equals(show.getTitle()) && list.get(i).getNetwork().equals(show.getNetwork())) {
					flag = true;
					model.addAttribute("titleAndNetwork", "Title and Network combination exists");
				}
			}
			if(flag) {
				return "new.jsp";
			} else {
				Long userid = (Long) session.getAttribute("userid");
				User u = urepo.findById(userid).orElse(null);
				
				show.setCreator(u);
				srepo.save(show);
				return "redirect:/shows";
			}
			
		}
	}

	@GetMapping("/shows/{show_id}")
	public String details(HttpSession session, Model model, @PathVariable("show_id") Long sid) {
		Long userid = (Long) session.getAttribute("userid");
		User u = urepo.findById(userid).orElse(null);
		boolean showForm = true;

		Show s = srepo.findById(sid).orElse(null);
		for(int i = 0; i < s.getRatings().size(); i++) {
			if(u.equals(s.getRatings().get(i).getRater())) {
				showForm = false;
				break;
			}
		}
		s.getRatings().sort((r1, r2)-> r1.getRating() - r2.getRating()); 
		model.addAttribute("me", u);
		model.addAttribute("show", s);
		model.addAttribute("ratingObject", new Rating());
		model.addAttribute("showForm", showForm);
		return "details.jsp";
	}
	@PostMapping("/rate/{show_id}")
	public String rateShow(Model model, HttpSession session, @PathVariable("show_id") Long sid, @Valid @ModelAttribute("ratingObject") Rating rating, BindingResult result) {
		if(result.hasErrors()) {
			Long userid = (Long) session.getAttribute("userid");
			User u = urepo.findById(userid).orElse(null);
			model.addAttribute("me", u);
			model.addAttribute("show", srepo.findById(sid).orElse(null));
			return "details.jsp";
		} else {
			
			Long userid = (Long) session.getAttribute("userid");
			User u = urepo.findById(userid).orElse(null);
			
			rating.setRater(u);
			rating.setShow(srepo.findById(sid).orElse(null));
			rrepo.save(rating);
			return "redirect:/shows/" + sid;
		}
	}
	@GetMapping("/shows/{show_id}/edit")
	public String showEdit(@PathVariable("show_id") Long sid, Model model) {
		model.addAttribute("show", srepo.findById(sid).orElse(null));
		return "edit.jsp";
	}
	
	@PostMapping("/shows/{show_id}/edit")
	public String doEdit(@PathVariable("show_id") Long sid, @Valid @ModelAttribute("show") Show show, BindingResult result) {
		if(result.hasErrors()) {
			show.setId(sid);
			return "edit.jsp";
		} else {
			Show original = srepo.findById(sid).orElse(null);
			original.setTitle(show.getTitle());
			original.setNetwork(show.getNetwork());
			srepo.save(original);
			return "redirect:/shows";
		}
	}
	
	@GetMapping("/delete/{show_id}")
	public String deleteShow(@PathVariable("show_id") Long sid) {
		srepo.deleteById(sid);
		return "redirect:/shows";
	}
}
