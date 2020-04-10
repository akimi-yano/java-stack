package com.example.greatideas.servs;

import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;

import com.example.greatideas.models.Idea;
import com.example.greatideas.models.User;
import com.example.greatideas.repos.IdeaRepo;



@Service
public class IdeaServ {
	private final IdeaRepo irepo;
	public IdeaServ(IdeaRepo irepo) {
		this.irepo = irepo;
	}
	public Idea findOne(Long id) {
		Optional<Idea> optional = irepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
	
	public void like(Idea likedIdea, User likedUser) {
		List<User> likedUsers = likedIdea.getLiked_users();
		if (!likedUsers.contains(likedUser)) {
			likedUsers.add(likedUser);
			likedIdea.setLiked_users(likedUsers);
			irepo.save(likedIdea);
		}
	}
	
	public void unlike(Idea likedIdea, User likedUser) {
		List<User> likedUsers = likedIdea.getLiked_users();
		if (likedUsers.contains(likedUser)) {
			likedUsers.remove(likedUser);
			likedIdea.setLiked_users(likedUsers);
			irepo.save(likedIdea);
		}
	}
}
