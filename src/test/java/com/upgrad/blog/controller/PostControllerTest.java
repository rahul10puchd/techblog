package com.upgrad.blog.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.upgrad.blog.model.Post;
import com.upgrad.blog.model.User;
import com.upgrad.blog.repository.PostRepository;
import com.upgrad.blog.service.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PostService postService;

    @MockBean
    private CategoryService categoryService;

    @MockBean
    private UserDetailsServiceImpl userDetailsServiceImpl;


    @MockBean
    private PostRepository postRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
    }


    @Test
    @WithMockUser("user-1")
    public void getPosts() throws Exception {

        User user = new User();

        Mockito.when(userService.getCurrentLoggedINUser()).thenReturn(user);

        String uri = "/posts";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(uri))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        Assert.assertEquals(200, result.getResponse().getStatus());
   }

   @Test
   @WithMockUser("user-1")
   public void addPosts() throws Exception {

       User user = new User();

       Mockito.when(userService.getCurrentLoggedINUser()).thenReturn(user);
       String uri = "/posts";

       Post post = new Post();
       post.setTitle("Pehla");
       post.setBody("Pehla Post");
       post.setId(1);

       ObjectMapper mapper = new ObjectMapper();
       mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
       ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
       String requestJson = ow.writeValueAsString(post);

       MvcResult res = mockMvc.perform(MockMvcRequestBuilders.post(uri)
               .contentType(MediaType.APPLICATION_JSON)
               .content(requestJson))
               .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

       Assert.assertEquals(200, res.getResponse().getStatus());
   }

}
