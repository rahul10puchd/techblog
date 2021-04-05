package com.upgrad.blog.repository;

import com.upgrad.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}