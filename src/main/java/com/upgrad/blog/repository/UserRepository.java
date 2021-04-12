package com.upgrad.blog.repository;

import com.upgrad.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserName(String userName);
}