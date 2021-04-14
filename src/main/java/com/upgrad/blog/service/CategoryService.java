package com.upgrad.blog.service;

import com.upgrad.blog.model.Category;
import com.upgrad.blog.model.User;
import com.upgrad.blog.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public Category getCategoryByName(String name){
        Optional<Category> category = categoryRepository.findByCategory(name);
        if(category.isPresent()){
            return category.get();
        }else{
            return null;
        }

    }
}
