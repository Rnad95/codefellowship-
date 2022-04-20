package com.example.codefellowship.repositories;

import com.example.codefellowship.models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Integer> {
    ApplicationUser findByUsername(String username);
    ApplicationUser findById(Long Id);
    List<ApplicationUser> findAll();

//    List<ApplicationUser> findAll(String keyword);
}
