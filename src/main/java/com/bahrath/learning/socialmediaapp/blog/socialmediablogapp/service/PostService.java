package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

   PostDto createPost(PostDto postDto);

    PostDto updatePostById(Long id,PostDto postDto);

   void deletePostById(Long id);

}
