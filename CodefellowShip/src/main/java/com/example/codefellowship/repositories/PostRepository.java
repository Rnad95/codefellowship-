package com.example.codefellowship.repositories;

import com.example.codefellowship.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface PostRepository extends CrudRepository<Post,Integer> {
    Post findAllById(int Id);
    Set<Post> findAll();
}
