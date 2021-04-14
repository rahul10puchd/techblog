package com.upgrad.blog.repository;

import com.upgrad.blog.model.Post;
import com.upgrad.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Optional<User> findByUserId(int id);
}