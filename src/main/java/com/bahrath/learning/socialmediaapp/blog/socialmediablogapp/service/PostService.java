package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto> getAllPosts();

    PostDto getPostById(Long id);

   PostDto createPost(PostDto postDto);

    PostDto updatePostById(PostDto postDto,Long id);

   void deletePostById(Long id);

}
