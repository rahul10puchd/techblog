package com.upgrad.blog.controller;

import com.upgrad.blog.exception.InvalidPostException;
import com.upgrad.blog.model.Post;
import com.upgrad.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    @Autowired
    private PostService postService;

    /*
    /posts      - get
    /posts/id   - get
    /posts      - post
    /posts/id   - delete
    /posts/id   - put
    */
    //@PreAuthorize("hasRole('ADMIN')") this can used at both method level and at class level
    //We have used it at method level in our example
    //hasAnyRole also can be used here instead of hasRole

    //Here we will catch errors thrown by service
    @RequestMapping("/posts")   //localhsot:8080/posts - get (response is json)
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> posts = null;
        Map<String, Object> body = new LinkedHashMap<>();
        try {
            posts = postService.getAllPosts();
        }
        catch (InvalidPostException e) {
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Post no found");

            return new ResponseEntity(body, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(posts);
    }

    //This is an example for throwing errors till end & not catching anywhere
    @RequestMapping("/getpostbyid/{id}")
    public Post getPost(@PathVariable Integer id) throws InvalidPostException {
        return this.postService.getPost(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/posts/create")
    public String addPost(@RequestBody Post post){
        System.out.println(post.getTitle());//temp
        post.setDate(new Date());
        postService.addPost(post);
        String response ="{\"success\":true,\"message\":\"Post has been added successfully\"}";
        return response;
    }
    @DeleteMapping("/posts/delete/{id}")
    public String deletePosts(@PathVariable Integer id){
        this.postService.deletePost(id);
        String response="{\"success\":true,\"message\":\"Post has been deleted successfully\"}";
        return response;
    }


}
