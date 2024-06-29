package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.controller;


import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.payload.PostResponse;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/post")
public class PostController {


    @Autowired
    private PostService postService;


//pagination
//    @GetMapping
//    public PostResponse getAllPosts(
//            @RequestParam (value="pageNo",defaultValue ="0",required=false)int pageNo,
//            @RequestParam(value="pageSize",defaultValue="5", required=false) int pageSize){
//       return postService.getAllPosts(pageNo,pageSize);
//
//    }


    //pagination & sorting
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam (value="pageNo",defaultValue ="0",required=false)int pageNo,
            @RequestParam(value="pageSize",defaultValue="5", required=false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDirection", defaultValue = "ASC", required = false)
            String sortDirection) {
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDirection);

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


    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable Long id,@RequestBody PostDto postDto){
       PostDto updatedPostDto= postService.updatePostById(id,postDto);
       return new ResponseEntity<>(updatedPostDto,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Deleted successfully post resource::"+id,HttpStatus.OK);
    }

}

