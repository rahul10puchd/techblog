package com.upgrad.blog.controller;

import com.upgrad.blog.model.Post;
import com.upgrad.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class HomeController {
    @Autowired
    private PostService postService;
    @RequestMapping("/")   //localhsot:8080/posts - get (response is json)
    public List<Post> getAllPosts(){
        return this.postService.getAllPosts();
    }
}
