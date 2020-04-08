package com.example.demo.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Book;
import com.example.demo.repos.BookRepo;

@Service
public class BookServ {
	
	private final BookRepo brepo;
	
	public BookServ(BookRepo brepo) {
		this.brepo = brepo;
	}
	
	public Book findOne(Long id) {
		Optional<Book> optional = brepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	
}
