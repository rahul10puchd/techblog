package com.upgrad.blog.controller;

import com.upgrad.blog.model.Category;
import com.upgrad.blog.model.Post;
import com.upgrad.blog.model.User;
import com.upgrad.blog.service.CategoryService;
import com.upgrad.blog.service.PostService;
import com.upgrad.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RestController
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;
//userService.getCurrentLoggedINUser()
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
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/posts")   //localhsot:8080/posts - get (response is json)
    public List<Post> getUserPost(){
        User user = userService.getCurrentLoggedINUser();
        List<Post> posts= user.getPost();
        return posts;
    }
    @RequestMapping("/posts/{id}")
    public Post getPost(@PathVariable Integer id){
        return this.postService.getPost(id);
    }

    @RequestMapping(method = RequestMethod.POST, value="/posts")
    public String addPost(@RequestBody Post post){
        User user = userService.getCurrentLoggedINUser();
        post.setUser(user);
        if(post.getCategory()!=null){
            Category category=categoryService.getCategoryByName(post.getCategory());
            if(category!=null){
                post.getCategories().add(category);
            }else{
                category= new Category();
                category.setCategory(post.getCategory());
                post.getCategories().add(category);
            }

        }
        post.setDate(new Date());
        postService.addPost(post);
        String response ="{\"success\":true,\"message\":\"Post has been added successfully\"}";
        return response;
    }
    @DeleteMapping("/posts/{id}")
    public String deletePosts(@PathVariable Integer id){
        this.postService.deletePost(id);
        String response="{\"success\":true,\"message\":\"Post has been deleted successfully\"}";
        return response;
    }


}
