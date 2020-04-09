package com.example.demo.controllers;

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

import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repos.TaskRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.servs.TaskServ;
import com.example.demo.servs.UserServ;
import com.example.demo.validators.UserValidator;

@Controller
public class MainController {
	private final UserRepo urepo;
	private final UserServ userv;
	private final TaskRepo trepo;
	private final TaskServ tserv;
	private final UserValidator uvalid;
	public MainController(
			UserRepo urepo,
			UserServ userv,
			TaskRepo trepo,
			TaskServ tserv,
			UserValidator uvalid
			) {
		this.urepo = urepo;
		this.userv = userv;
		this.trepo = trepo;
		this.tserv = tserv;
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
	
	@GetMapping("/dash")
	public String dashboard(HttpSession session, Model model) {
		Long userid = (Long) session.getAttribute("userid");
		Optional<User> maybeU = userv.findOne(userid);

		model.addAttribute("user", maybeU.isPresent() ? maybeU.get() : new User());
		List<Task> mylist = trepo.findAll();
		model.addAttribute("tasks",mylist);
		return "dashboard.jsp";
	}
	
	@GetMapping("/newTask")
	public String newTask(Model model) {
		model.addAttribute("task", new Task());
		return "new.jsp";
	}
	
	@PostMapping("/newTask")
	public String createTask(HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			Optional<User> maybeU = userv.findOne((Long) session.getAttribute("userid"));
			task.setCreator(maybeU.isPresent() ? maybeU.get() : new User());
			trepo.save(task);
			return "redirect:/dash";
		}
	}
	
	@GetMapping("/edit/{task_id}")
	public String editPage(Model model, @PathVariable("task_id") Long taskId) {
		// prepopulation (: black belt
		Task t = tserv.findOne(taskId);
		model.addAttribute("task", t);
		return "edit.jsp";
	}
	
	@PostMapping("/edit/{task_id}")
	public String doEdit(Model model, @PathVariable("task_id") Long taskId, @Valid @ModelAttribute("task") Task task, BindingResult result) {
//		System.out.println(book.getId());
		if(result.hasErrors()) {
			task.setId(taskId);
			return "edit.jsp";
			
		} else {
			// form data doesn't include the creator id, so get it and set it
			User taskCreator = tserv.findOne(taskId).getCreator();
			task.setCreator(taskCreator);

			task.setId(taskId);
			trepo.save(task);
			return "redirect:/dash";
		}
	}
	
	
	@GetMapping("/delete/{task_id}")
	public String deleteTask(@PathVariable("task_id") Long id, HttpSession session) {
		Long uid = (Long) session.getAttribute("userid");
		Task t = tserv.findOne(id);
		
		boolean idsEqual = t.getCreator() != null
				&& t.getCreator().getId().longValue() == uid.longValue();
		if(!idsEqual) {
			System.out.println("idsEqual: " + idsEqual + "; task creator=" + t.getCreator().getId() + ", user id=" + uid);
			return "redirect:/dash";
		} else {
			trepo.deleteById(id);
			return "redirect:/dash";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
