package com.upgrad.blog.service;

import com.upgrad.blog.model.Post;
import com.upgrad.blog.model.User;
import com.upgrad.blog.model.UserDetailsImpl;
import com.upgrad.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentLoggedINUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            System.out.println("principal instanceof UserDetails");
            username = ((UserDetails)principal).getUsername();
        } else {
            System.out.println("principal is not instanceof UserDetails");
            username = principal.toString();
        }
        Optional<User> user = userRepository.findByUserName(username);
        user.orElseThrow(() -> new UsernameNotFoundException(username+ "Not Found"));
        return user.map(User::new).get();
    }
    public boolean isExistingUser(String userName){
        Optional<User> user = userRepository.findByUserName(userName);
        return user.isPresent();
    }
    public void save(User user){
        this.userRepository.save(user);
    }
}
