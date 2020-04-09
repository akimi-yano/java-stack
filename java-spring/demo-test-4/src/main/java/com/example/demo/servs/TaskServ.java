package com.example.demo.servs;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Task;
import com.example.demo.repos.TaskRepo;

@Service
public class TaskServ {
	private final TaskRepo trepo;
	public TaskServ(TaskRepo trepo) {
		this.trepo = trepo;
	}
	public Task findOne(Long id) {
		Optional<Task> optional = trepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}
}
