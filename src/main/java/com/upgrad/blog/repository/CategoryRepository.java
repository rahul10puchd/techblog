package com.upgrad.blog.repository;

import com.upgrad.blog.model.Category;
import com.upgrad.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> findByCategory(String category);
}
