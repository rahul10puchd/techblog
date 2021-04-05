package com.upgrad.blog.service;

import com.upgrad.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PostService {
    @Autowired
    private PostRepository postRepository;
//    createPost,getposts, deletePost, updatePost
//    postRepository.save()
//    postRepository.delete()
//    postRepository.findAll()

}
