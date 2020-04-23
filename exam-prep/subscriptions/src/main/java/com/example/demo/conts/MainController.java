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

import com.example.demo.models.Package;
import com.example.demo.models.User;
import com.example.demo.repos.PackageRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.servs.UserServ;
import com.example.demo.validators.UserValidator;

@Controller
public class MainController {
	private final UserValidator userValidator;
	private final UserRepo urepo;
	private final UserServ userv;
	private final PackageRepo prepo;
	public MainController(
			UserValidator userValidator,
			UserRepo urepo,
			UserServ userv,
			PackageRepo prepo) {
		this.userValidator=userValidator;
		this.urepo=urepo;
		this.userv=userv;
		this.prepo=prepo;
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
			if(urepo.findAll().size() == 1) {
				u.setLevel(1);
			} else {
				u.setLevel(9);
				u.setSubscription(prepo.findById(Long.valueOf(1)).orElse(null));
			}
			urepo.save(u);
			session.setAttribute("userid", u.getId());
			if(u.getLevel() == 9) {
				return "redirect:/users/" + u.getId();
			} else {
				return "redirect:/packages";
			}			
		}
    }
	
	@PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(userv.authenticateUser(email, password)) {
			User u = urepo.findByEmail(email);
			session.setAttribute("userid", u.getId());
			return "redirect:/packages";
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
	
	@GetMapping("/packages")
	public String adminDash(HttpSession session, Model model) {
		Long userid = (Long)session.getAttribute("userid");
		if(urepo.findById(userid).orElse(null).getLevel() == 1) {
			List<Package> list = prepo.findAll();
			list.sort((p1, p2)-> p2.getSubscribers().size() - p1.getSubscribers().size());
			model.addAttribute("users", urepo.findAll());
			model.addAttribute("packages", list);
			model.addAttribute("active" , "available");
			model.addAttribute("inactive", "unavailable");
			model.addAttribute("package", new Package());
			
			return "dashboard.jsp";
		} else {
			return "redirect:/users/" + userid;
		}
	}
	
	@PostMapping("/packages")
	public String doCreatePack(Model model, @Valid @ModelAttribute("package") Package p, BindingResult result) {
		if(result.hasErrors()) {
			List<Package> list = prepo.findAll();
			list.sort((p1, p2)-> p2.getSubscribers().size() - p1.getSubscribers().size());
			model.addAttribute("users", urepo.findAll());
			model.addAttribute("packages", list);
			model.addAttribute("active" , "available");
			model.addAttribute("inactive", "unavailable");
			return "dashboard.jsp";
		} else {
			p.setStatus("available");
			prepo.save(p);
			return "redirect:/packages";
		}
	}
	@GetMapping("/activate/{package_id}")
	public String actPack(@PathVariable("package_id") Long id) {
		Package p = prepo.findById(id).orElse(null);
		p.setStatus("available");
		prepo.save(p);
		return "redirect:/packages";
	}
	@GetMapping("/deactivate/{package_id}")
	public String deactPack(@PathVariable("package_id") Long id) {
		Package p = prepo.findById(id).orElse(null);
		p.setStatus("unavailable");
		prepo.save(p);
		return "redirect:/packages";
	}
	
	@GetMapping("/packages/{package_id}/edit")
	public String showEdit(Model model, HttpSession session, @PathVariable("package_id") Long id) {
		Long userid = (Long) session.getAttribute("userid");
		User u = urepo.findById(userid).orElse(null);
		if(u.getLevel() != 1) {
			return "redirect:/users/" + u.getId();
		} else {
			Package p = prepo.findById(id).orElse(null);
			model.addAttribute("pack", p);
			return "edit.jsp";
		}
	}
	
	@PostMapping("/packages/{package_id}/edit")
	public String doEdit(@Valid @ModelAttribute("pack") Package p, BindingResult result, @PathVariable("package_id") Long id) {
		if(result.hasErrors()) {
			p.setId(id);
			return "edit.jsp";
		} else {
			Package original = prepo.findById(id).orElse(null);
			original.setCost(p.getCost());
			prepo.save(original);
			return "redirect:/packages";
		}
	}
	
	@GetMapping("/delete/{package_id}")
	public String doDelete(HttpSession session, @PathVariable("package_id") Long id) {
		Long userid = (Long) session.getAttribute("userid");
		User u = urepo.findById(userid).orElse(null);
		if(u.getLevel() != 1) {
			return "redirect:/users/" + u.getId();
		} else {
			prepo.deleteById(id);
			return "redirect:/packages";
		}
	}
	@GetMapping("/users/{user_id}")
	public String showDetails(Model model, @PathVariable("user_id") Long id) {
		User u = urepo.findById(id).orElse(null);
		model.addAttribute("me", u);
		model.addAttribute("packages", prepo.findAll());
		return "details.jsp";
	}
	
	@PostMapping("/users/{user_id}")
	public String doDetailUpdate(@ModelAttribute("me") User fromForm, @PathVariable("user_id") Long id) {
		System.out.println(fromForm.getSubscription().getName());
		User original = urepo.findById(id).orElse(null);
		original.setSubscription(fromForm.getSubscription());
		urepo.save(original);
		return "redirect:/users/" +id;
	}

}
