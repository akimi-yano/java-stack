package com.example.demo.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.Book;
import com.example.demo.repos.BookRepo;
import com.example.demo.services.BookServ;

@Controller
public class MainController {
	
	private final BookRepo brepo;
	private final BookServ bserv;
	
	public MainController(
			BookRepo brepo,
			BookServ bserv
			) {
		this.brepo = brepo;
		this.bserv = bserv;
	}
	
	@GetMapping("/")
	public String index(Model model) {
		List<Book> mylist = brepo.findAll();
		model.addAttribute("books", mylist);
		return "index.jsp";
	}
	
	@GetMapping("/new")
	public String createPage(Model model) {
		model.addAttribute("book", new Book());
		return "new.jsp";
	}
	
	@PostMapping("/new")
	public String doCreate(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		if(result.hasErrors()) {
			return "new.jsp";
		} else {
			brepo.save(book);
			return "redirect:/";
		}
	}
	
	@GetMapping("/edit/{book_id}")
	public String editPage(Model model, @PathVariable("book_id") Long id) {
		// prepopulation (: black belt
		Book b = bserv.findOne(id);
		model.addAttribute("book", b);
		return "edit.jsp";
	}
	
	@PostMapping("/edit/{book_id}")
	public String doEdit(Model model, @PathVariable("book_id") Long id, @Valid @ModelAttribute("book") Book book, BindingResult result) {
//		System.out.println(book.getId());
		if(result.hasErrors()) {
			book.setId(id);
			return "edit.jsp";
		} else {
			book.setId(id);
			brepo.save(book);
			return "redirect:/";
		}
	}
	
	@GetMapping("/delete/{book_id}")
	public String deleteBook(@PathVariable("book_id") Long id) {
		brepo.deleteById(id);
		return "redirect:/";
	}
}
