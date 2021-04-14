package com.upgrad.blog.controller;

import com.upgrad.blog.model.*;
import com.upgrad.blog.service.JwtUtil;
import com.upgrad.blog.service.UserDetailsServiceImpl;
import com.upgrad.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {

        try {
            //used for authenticate based pn provided username and password - that's it
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }
        //till here we are OK..i.e, a valid user.

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        //ResponseEntity.ok ..here ok implies StatusCode 200
        return ResponseEntity.ok(new LoginResponse(jwt));
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignupRequest newUser) {
        if(userService.isExistingUser(newUser.getUserName())){
            return new ResponseEntity<>("Bhak ...", HttpStatus.CONFLICT);
        }

        UserProfile userProfile=new UserProfile();
        userProfile.setAddress(newUser.getAddress());
        userProfile.setEmailAddress(newUser.getEmailAddress());
        userProfile.setFullName(newUser.getFullName());
        userProfile.setGender(newUser.getGender());
        userProfile.setMobileNumber(newUser.getMobileNumber());

        User user=new User();
        user.setProfile(userProfile);
        user.setActive(true);
        user.setRole("ROLE_USER");
        user.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        user.setUserName(newUser.getUserName());

        if(newUser.getUserName().equals("admin")){
            user.setRole("ROLE_ADMIN");
        }
        userService.save(user);
        return new ResponseEntity<>("ALL SET", HttpStatus.CREATED);
    }
}
