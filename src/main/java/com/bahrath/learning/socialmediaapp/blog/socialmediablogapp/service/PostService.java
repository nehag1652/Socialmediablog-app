package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.payload.PostResponse;

import java.util.List;

public interface PostService {

    //List<PostDto> getAllPosts();

    //PostResponse getAllPosts(int pageNo, int pageSize);

    PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDirection);

    PostDto getPostById(Long id);

   PostDto createPost(PostDto postDto);

    PostDto updatePostById(Long id,PostDto postDto);

   void deletePostById(Long id);

}
