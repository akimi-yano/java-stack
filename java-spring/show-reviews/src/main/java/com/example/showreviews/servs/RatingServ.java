package com.example.showreviews.servs;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.showreviews.models.Rating;
import com.example.showreviews.repos.RatingRepo;

@Service
public class RatingServ {
	private final RatingRepo rrepo;
	public RatingServ(RatingRepo rrepo) {
		this.rrepo = rrepo;
	}
	
	public Rating findOne(Long id) {
		Optional<Rating> maybeU = rrepo.findById(id);
		if (maybeU.isPresent()) {
			return maybeU.get();
		}
		return null;
    }
}
