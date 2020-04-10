package com.example.showreviews.controllers;

import java.util.List;
import java.util.Optional;

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

import com.example.showreviews.models.Rating;
import com.example.showreviews.models.Show;
import com.example.showreviews.models.User;
import com.example.showreviews.repos.RatingRepo;
import com.example.showreviews.repos.ShowRepo;
import com.example.showreviews.repos.UserRepo;
import com.example.showreviews.servs.RatingServ;
import com.example.showreviews.servs.ShowServ;
import com.example.showreviews.servs.UserServ;
import com.example.showreviews.validators.UserValidator;

@Controller
public class MainController {
	private final UserRepo urepo;
	private final UserServ userv;
	private final ShowRepo srepo;
	private final ShowServ sserv;
	private final RatingRepo rrepo;
	private final RatingServ rserv;
	private final UserValidator uvalid;
	public MainController(
			UserRepo urepo,
			UserServ userv,
			ShowRepo srepo,
			ShowServ sserv,
			RatingRepo rrepo,
			RatingServ rserv,
			UserValidator uvalid
			) {
		this.urepo = urepo;
		this.userv = userv;
		this.srepo = srepo;
		this.sserv = sserv;
		this.rrepo = rrepo;
		this.rserv = rserv;
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
		List<Show> mylist = srepo.findAll();
		mylist.forEach(show -> {
			List<Rating> ratings = rrepo.findByShow(show);
			if (ratings.size() > 0) {
				Double score = 0.0;
				for (Rating rating : ratings) {
					score += rating.getRate();
				}
				score /= ratings.size();
				show.setAverageRating(score);
			}
		});

		model.addAttribute("shows",mylist);
		return "dashboard.jsp";
	}
	
	@GetMapping("/new")
	public String newShow(Model model) {
		model.addAttribute("show", new Show());
		return "new.jsp";
	}
	
	@PostMapping("/new")
	public String createIdea(HttpSession session, @Valid @ModelAttribute("show") Show show, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {

			srepo.save(show);
			return "redirect:/dash";
		}
	}
	@GetMapping("/show/{show_id}")
	public String showPage(Model model, @PathVariable("show_id") Long showId) {
		Show s = sserv.findOne(showId);
		model.addAttribute("show", s);
		model.addAttribute("newRating", new Rating());
		model.addAttribute("ratings", rrepo.findByShowOrderByRateAsc(s));
		return "show.jsp";
	}
	@PostMapping("/show/{show_id}")
	public String addRating(HttpSession session, Model  model, @PathVariable("show_id") Long showId, @Valid @ModelAttribute("newRating") Rating newRating, BindingResult result) {
		if(result.hasErrors()) {
			Show s = sserv.findOne(showId);
			model.addAttribute("show", s);
			model.addAttribute("ratings", rrepo.findByShowOrderByRateAsc(s));
			return "show.jsp";
		} else {
		User u = userv.findOne((Long) session.getAttribute("userid"));
		Show s = sserv.findOne(showId);
		if (!s.getRated_users().contains(u)) {
			newRating.setRated_users(u);
			newRating.setRated_shows(s);		
			rrepo.save(newRating);
		}
		return "redirect:/show/{show_id}";
	}
	}
		
	
	@GetMapping("/edit/{show_id}")
	public String editPage(Model model, @PathVariable("show_id") Long showId) {
		// populate  the  value beforehand
		Show s = sserv.findOne(showId);
		model.addAttribute("show", s);
		return "edit.jsp";
	}
	
	@PostMapping("/edit/{show_id}")
	public String doEdit(Model model, @PathVariable("show_id") Long showId, @Valid @ModelAttribute("show") Show show, BindingResult result) {
		if(result.hasErrors()) {
			show.setId(showId);
			return "edit.jsp";
			
		} else {
			List <User> showRaters = sserv.findOne(showId).getRated_users();
			show.setRated_users(showRaters);
			show.setId(showId);
			srepo.save(show);
			return "redirect:/show/{show_id}";
		}
	}
	
	
	@GetMapping("/delete/{show_id}")
	public String deleteIdea(@PathVariable("show_id") Long showId, HttpSession session) {
			srepo.deleteById(showId);
			return "redirect:/dash";
//		}
	}
	


	
}



	
	

	
