package com.example.demo.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

import com.example.demo.models.Course;
import com.example.demo.models.User;
import com.example.demo.models.UserCourse;
import com.example.demo.repos.CourseRepo;
import com.example.demo.repos.UserCourseRepo;
import com.example.demo.repos.UserRepo;
import com.example.demo.servs.UserServ;
import com.example.demo.validators.UserValidator;

@Controller
public class MainController {
	private final UserRepo urepo;
	private final UserServ userv;
	private final CourseRepo crepo;
	private final UserCourseRepo ucrepo;
	private final UserValidator userValidator;
	public MainController(UserRepo urepo, UserServ userv, CourseRepo crepo, UserCourseRepo ucrepo, UserValidator userValidator) {
		this.urepo = urepo;
		this.userv = userv;
		this.crepo = crepo;
		this.ucrepo = ucrepo;
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
			return "redirect:/courses";
		}
    }
	
	@PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
		if(userv.authenticateUser(email, password)) {
			User u = urepo.findByEmail(email);
			session.setAttribute("userid", u.getId());
			return "redirect:/courses";
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
	
//	----------
	@GetMapping("/courses/new")
	public String showCourseForm(Model model) {
		model.addAttribute("course", new Course());
		return "new.jsp";
	}
	
	@PostMapping("/courses/new")
	public String doCourseForm(HttpSession session, @Valid @ModelAttribute("course") Course course, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			Long id = (Long) session.getAttribute("userid");
			User u = urepo.findById(id).orElse(null);
			course.setCreator(u);
			crepo.save(course);
			return "redirect:/courses";
		}
	}
	
	@GetMapping("/courses")
	public String dashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("userid");
		model.addAttribute("courses", crepo.findAll());
		model.addAttribute("me", urepo.findById(id).orElse(null));
		return "courses.jsp";
	}
	
	@GetMapping("/add/{course_id}")
	public String addCourse(@PathVariable("course_id") Long cid, HttpSession session) {
		//finding what u need
		Long userid = (Long) session.getAttribute("userid");
		User u = urepo.findById(userid).orElse(null);
		Course c = crepo.findById(cid).orElse(null);
		// creating the join manually for the createdAT
		UserCourse uc = new UserCourse();
		uc.setCourse(c);
		uc.setUser(u);
		ucrepo.save(uc);
		return "redirect:/courses";
	}
	
	@GetMapping("/remove/{course_id}")
	public String removeCourse(@PathVariable("course_id") Long cid, HttpSession session) {
		Long userid = (Long) session.getAttribute("userid");
		User u = urepo.findById(userid).orElse(null);
		
		Course c = crepo.findById(cid).orElse(null);
		List<UserCourse> originalList = ucrepo.findAll();
		for(int i = originalList.size() - 1; i > -1;i--) {
			if(originalList.get(i).getCourse().equals(c) && originalList.get(i).getUser().equals(u)) {
				System.out.println(originalList.get(i).getId());
				ucrepo.deleteById(originalList.get(i).getId());
				break;
			}
		}
		return "redirect:/courses/" + cid;
	}
	
	@GetMapping("/courses/{course_id}/edit")
	public String showEditForm(@PathVariable("course_id")Long id,Model model) {
		model.addAttribute("course", crepo.findById(id).orElse(null));
		return "edit.jsp";
	}
	
	@PostMapping("/courses/{course_id}/edit")
	public String doCourseForm(@PathVariable("course_id")Long id,HttpSession session, @Valid @ModelAttribute("course") Course course, BindingResult result) {
		if(result.hasErrors()) {
			Course original = crepo.findById(id).orElse(null);
			course.setId(id);
			course.setName(original.getName());
			return "edit.jsp";
		} else {
			// creator, joinedUsers
			Course original = crepo.findById(id).orElse(null);
			original.setName(course.getName());
			original.setCapacity(course.getCapacity());
			original.setInstructor(course.getInstructor());
			crepo.save(original);
			return "redirect:/courses";
		}
	}
	
	@GetMapping("/courses/{course_id}")
	public String showDetailsPage(@PathVariable("course_id") Long id, Model model) {
		List<UserCourse> originalList = ucrepo.findAll();
		for(int i = originalList.size() - 1; i > -1;i--) {
			if(originalList.get(i).getCourse().getId() != id) {
				originalList.remove(i);
			}
		}
		model.addAttribute("joiners", originalList);
		model.addAttribute("course", crepo.findById(id).orElse(null));
		return "details.jsp";
	}
	
	@GetMapping("/lowsign")
	public String lowDashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("userid");
		List<Course> mylist = crepo.findAll();
		mylist.sort((c1, c2) -> c1.getJoinedUsers().size() - c2.getJoinedUsers().size());
		model.addAttribute("courses", mylist);
		model.addAttribute("me", urepo.findById(id).orElse(null));
		return "courses.jsp";
	}
	
	@GetMapping("/highsign")
	public String highDashboard(Model model, HttpSession session) {
		Long id = (Long) session.getAttribute("userid");
		List<Course> mylist = crepo.findAll();
		mylist.sort((c1, c2) -> c2.getJoinedUsers().size() - c1.getJoinedUsers().size());
		model.addAttribute("courses", mylist);
		model.addAttribute("me", urepo.findById(id).orElse(null));
		return "courses.jsp";
	}
	
	@GetMapping("/signup/asc/{course_id}")
	public String detailsASC(@PathVariable("course_id") Long id, Model model) {
		List<UserCourse> originalList = ucrepo.findAll();
		for(int i = originalList.size() - 1; i > -1;i--) {
			if(originalList.get(i).getCourse().getId() != id) {
				originalList.remove(i);
			}
		}
		Collections.sort(originalList, new Comparator<UserCourse>() {
			  @Override
			  public int compare(UserCourse u1, UserCourse u2) {
			    return u1.getCreatedAt().compareTo(u2.getCreatedAt());
			  }
			});
		model.addAttribute("joiners", originalList);
		model.addAttribute("course", crepo.findById(id).orElse(null));
		return "details.jsp";
	}
	
	@GetMapping("/signup/desc/{course_id}")
	public String detailsDESC(@PathVariable("course_id") Long id, Model model) {
		List<UserCourse> originalList = ucrepo.findAll();
		for(int i = originalList.size() - 1; i > -1;i--) {
			if(originalList.get(i).getCourse().getId() != id) {
				originalList.remove(i);
			}
		}
		Collections.sort(originalList, new Comparator<UserCourse>() {
			  @Override
			  public int compare(UserCourse u1, UserCourse u2) {
			    return u2.getCreatedAt().compareTo(u1.getCreatedAt());
			  }
		});
		model.addAttribute("joiners", originalList);
		model.addAttribute("course", crepo.findById(id).orElse(null));
		return "details.jsp";
	}
}
