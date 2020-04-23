package com.example.demo.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Package;

@Repository
public interface PackageRepo extends CrudRepository<Package, Long>{
	List<Package> findAll();
}
