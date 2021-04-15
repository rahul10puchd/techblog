package com.upgrad.blog.controller;

import com.upgrad.blog.model.Post;
import com.upgrad.blog.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@Api(value = "Post CONTROLLER", description = "This is user Controller")
public class PostController {

    @Autowired
    private PostService postService;


    @GetMapping("/posts")
    @ApiOperation(value = "GET Post", notes = "GET all the Post")
    public List<Post> getAllPosts()
    {
        return this.postService.getAllPosts();
    }
    @GetMapping ("/posts/{id}")
    @ApiOperation(value = "GET Authors", notes = "GET all the Post By id")
    public Post getPost(@PathVariable Integer id)
    {
        return this.postService.getPost(id);
    }
    @RequestMapping(method = RequestMethod.POST, value="/posts")
    @ApiOperation(value = "POST ", notes = "Add the post ")
    public String addPost(@RequestBody Post post)
    {
        System.out.println(post.getTitle());
        post.setDate(new Date());
        postService.addPost(post);
        String response ="{\"success\":true,\"message\":\"Post has been added successfully\"}";
        return response;
    }
    @DeleteMapping("/posts/{id}")
            @ApiOperation(value = "Delete ", notes = "Delete the post By Id")
    public String deletePosts(@PathVariable Integer id)
    {
        this.postService.deletePost(id);
        String response="{\"success\":true,\"message\":\"Post has been deleted successfully\"}";
        return response;
    }


}
