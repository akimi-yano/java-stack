package com.example.greatideas.repos;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.greatideas.models.Idea;

@Repository
public interface IdeaRepo extends CrudRepository<Idea, Long>{
	List<Idea> findAll();
	List<Idea> findByOrderByLikesDesc();
	List<Idea> findByOrderByLikesAsc();
//    List<Idea> findAllByOrderByliked_usersDesc();
}



