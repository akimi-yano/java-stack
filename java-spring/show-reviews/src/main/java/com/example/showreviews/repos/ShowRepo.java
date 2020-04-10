package com.example.showreviews.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.showreviews.models.Show;

@Repository
public interface ShowRepo extends CrudRepository<Show, Long>{
	List<Show> findAll();
}



