package com.example.showreviews.servs;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.showreviews.models.Show;
import com.example.showreviews.models.User;
import com.example.showreviews.repos.ShowRepo;



@Service
public class ShowServ {
	private final ShowRepo srepo;
	public ShowServ(ShowRepo srepo) {
		this.srepo = srepo;
	}
	public Show findOne(Long id) {
		Optional<Show> optional = srepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
}
