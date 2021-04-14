package com.upgrad.blog.service;

import com.upgrad.blog.model.Post;
import com.upgrad.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
//getAllPosts, getPost, addPost, updatePost, deletePost
    public List<Post> getAllPosts(){
        return (List<Post>) this.postRepository.findAll();
    }
    public List<Post> getAllPosts(int id){
        return (List<Post>) this.postRepository.findAll();
    }

    public Post getPost(Integer id){
        //try typecasting and observe the execution
        Optional<Post> post = this.postRepository.findById(id);
        if(post.isPresent()){
            return post.get();
        }else{
            return null;
        }
    }
    public void addPost(Post post){
        this.postRepository.save(post);
    }
    public void deletePost(Integer id){
        this.postRepository.deleteById(id);
    }
    public boolean updatePost(Integer id, Post post){
        //check the Post by it's id
        //if it exists, then update
        //otherwise..do nothing
        if(this.postRepository.existsById(id)){
            this.postRepository.save(post);
            return true;
        }else{
            return false;
        }
    }


}
