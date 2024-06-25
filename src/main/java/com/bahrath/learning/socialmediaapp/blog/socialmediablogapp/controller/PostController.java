package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.controller;


import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model.PostEntity;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class PostController {


    @Autowired
    private PostService postService;


    @GetMapping
    public List<PostDto> getAllPosts(){
       return postService.getAllPosts();

    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto savedPostDto= postService.createPost(postDto);
        return new ResponseEntity<>(savedPostDto, HttpStatus.CREATED);
    }

}

