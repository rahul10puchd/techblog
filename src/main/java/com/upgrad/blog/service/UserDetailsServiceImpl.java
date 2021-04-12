package com.upgrad.blog.service;

import com.upgrad.blog.model.User;
import com.upgrad.blog.model.UserDetailsImpl;
import com.upgrad.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByUserName(userName);
        user.orElseThrow(() -> new UsernameNotFoundException(userName+ "Not Found"));
        return user.map(UserDetailsImpl::new).get();

    }
}
