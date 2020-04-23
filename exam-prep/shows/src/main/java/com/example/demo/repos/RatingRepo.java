package com.example.demo.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Rating;

@Repository
public interface RatingRepo extends CrudRepository<Rating, Long>{
	List<Rating> findAll();
}
