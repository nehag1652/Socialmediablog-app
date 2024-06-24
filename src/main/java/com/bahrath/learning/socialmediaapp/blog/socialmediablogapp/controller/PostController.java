package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.controller;


import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class PostController {


    @Autowired
    private PostService postService;

    public List<PostDto> getAllPosts(){
       return postService.getAllPosts();

    }

}

