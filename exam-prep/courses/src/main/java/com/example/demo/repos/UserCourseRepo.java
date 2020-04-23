package com.example.demo.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Course;
import com.example.demo.models.UserCourse;

@Repository
public interface UserCourseRepo extends CrudRepository<UserCourse, Long>{
	List<UserCourse> findAll();
}
