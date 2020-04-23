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

import com.example.demo.models.Task;
import com.example.demo.models.User;
import com.example.demo.repos.TaskRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.servs.UserServ;
import com.example.demo.validators.UserValidator;

@Controller
public class MainController {
	private final UserValidator userValidator;
	private final UserServ userv;
	private final UserRepo urepo;
	private final TaskRepo trepo;
	public MainController(UserValidator userValidator,
			UserServ userv,
			UserRepo urepo,
			TaskRepo trepo) {
		this.userValidator = userValidator;
		this.userv = userv;
		this.urepo = urepo;
		this.trepo = trepo;
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
			return "redirect:/tasks";
		}
    }
	
	@PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(userv.authenticateUser(email, password)) {
			User u = urepo.findByEmail(email);
			session.setAttribute("userid", u.getId());
			return "redirect:/tasks";
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
	
	@GetMapping("/tasks/new")
	public String showCreate(Model model) {
		model.addAttribute("task", new Task());
		model.addAttribute("people", urepo.findAll());
		return "new.jsp";
	}
	
	@PostMapping("/tasks/new")
	public String doCreate(Model model, HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
		if(result.hasErrors()) {
			model.addAttribute("people", urepo.findAll());
			return "new.jsp";
		}
		if(task.getAssignee().getAssignedTasks().size() == 3) {
			model.addAttribute("sizeError", "Assignee cannot be assigned more than 3 tasks");
			model.addAttribute("people", urepo.findAll());
			return "new.jsp";
		}
		User u = urepo.findById((Long)(session.getAttribute("userid"))).orElse(null);
		task.setCreator(u);
		trepo.save(task);
		return "redirect:/tasks";
	}
	
	@GetMapping("/tasks/{task_id}/edit")
	public String showEdit(HttpSession session, Model model,@PathVariable("task_id") Long id) {
		Long userid = (Long) session.getAttribute("userid");
		Task original = trepo.findById(id).orElse(null);
		if(original.getCreator().getId().equals(userid) == false) {
			return "redirect:/tasks";
		}
		model.addAttribute("task", trepo.findById(id).orElse(null));
		model.addAttribute("people", urepo.findAll());
		return "edit.jsp";
	}
	@PostMapping("/tasks/{task_id}/edit")
	public String doEdit(@PathVariable("task_id") Long id, Model model, HttpSession session, @Valid @ModelAttribute("task") Task task, BindingResult result) {
		Long userid = (Long) session.getAttribute("userid");
		Task original = trepo.findById(id).orElse(null);
		if(original.getCreator().getId().equals(userid) == false) {
			return "redirect:/tasks";
		}
		if(result.hasErrors()) {
			model.addAttribute("people", urepo.findAll());
			task.setId(id);
			return "edit.jsp";
		}
		if(task.getAssignee().getAssignedTasks().size() == 3) {
			model.addAttribute("sizeError", "Assignee cannot be assigned more than 3 tasks");
			model.addAttribute("people", urepo.findAll());
			task.setId(id);
			return "edit.jsp";
		}
		original.setName(task.getName());
		original.setAssignee(task.getAssignee());
		original.setPriority(task.getPriority());
		trepo.save(original);
		return "redirect:/tasks";
	}
	@GetMapping("/tasks")
	public String dashboard(HttpSession session, Model model) {
		model.addAttribute("tasks", trepo.findAll());
		model.addAttribute("me", urepo.findById((Long)(session.getAttribute("userid"))).orElse(null));
		
		return "dashboard.jsp";
	}
	@GetMapping("/high")
	public String sortHigh(HttpSession session, Model model) {
		List<Task> list = trepo.findAll();
		list.sort((t1, t2)->t2.getPriority() - t1.getPriority());
		model.addAttribute("tasks", list);
		model.addAttribute("me", urepo.findById((Long)(session.getAttribute("userid"))).orElse(null));
		return "dashboard.jsp";
	}
	@GetMapping("/low")
	public String sortLow(HttpSession session, Model model) {
		List<Task> list = trepo.findAll();
		list.sort((t1, t2)->t1.getPriority() - t2.getPriority());
		model.addAttribute("tasks", list);
		model.addAttribute("me", urepo.findById((Long)(session.getAttribute("userid"))).orElse(null));
		return "dashboard.jsp";
	}
	@GetMapping("/tasks/{task_id}")
	public String showDetails(Model model, @PathVariable("task_id") Long id) {
		model.addAttribute("task", trepo.findById(id).orElse(null));
		return "details.jsp";
	}
	@GetMapping("/delete/{task_id}")
	public String doDelete(@PathVariable("task_id") Long id) {
		trepo.deleteById(id);
		return "redirect:/tasks";
	}
}
