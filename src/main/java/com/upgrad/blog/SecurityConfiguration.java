package com.upgrad.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
//         /posts      - get
//                /posts/id   - get
//                /posts      - post
//                /posts/id   - delete
//                /posts/id   - put getpostbyid
        System.out.println("------------------------------------------");
        System.out.println("in configure(HttpSecurity httpSecurity)");
        httpSecurity.authorizeRequests()
                .antMatchers("/post/delete").hasRole("ADMIN")
                .antMatchers("/post/create").hasAnyRole("ADMIN", "USER")
                .antMatchers("/getpostbyid").hasAnyRole("ADMIN", "USER")
                .antMatchers("/posts").hasRole("ADMIN")
                .and().formLogin();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
