package com.example.showreviews.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.showreviews.models.Rating;
import com.example.showreviews.models.Show;
import com.example.showreviews.models.User;
@Repository
public interface RatingRepo extends CrudRepository<Rating, Long>{
	Optional<Rating> findById(Long id);
	List<Rating>  findAll();
	List<Rating>  findByShow(Show show);
	List<Rating> findByShowOrderByRateAsc(Show show);
	List<Rating>  findByUser(User user);
}
