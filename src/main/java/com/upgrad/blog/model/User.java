package com.upgrad.blog.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    private String password;
    private String role;
    private boolean active;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="profile_id")
    private UserProfile profile;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties(value = "user", allowSetters = true)
    private List<Post> post= new ArrayList<Post>();



    public User() {
        super();
    }

    public User(Integer id, String userName, String password, String role, boolean active, UserProfile profile, List<Post> post) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.active = active;
        this.profile = profile;
        this.post = post;
    }

    public User(User user) {
        this.id = user.id;
        this.userName = user.userName;
        this.password = user.password;
        this.role = user.role;
        this.active = user.active;
        this.profile = user.profile;
        this.post = user.post;
    }

    //    public User(Integer id, String userName, String password, String role, boolean active) {
//        this.id = id;
//        this.userName = userName;
//        this.password = password;
//        this.role = role;
//        this.active = active;
//    }
    public UserProfile getProfile() {
        return profile;
    }

    public void setProfile(UserProfile profile) {
        this.profile = profile;
    }

    public List<Post> getPost() {
        return post;
    }

    public void setPost(List<Post> post) {
        this.post = post;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
