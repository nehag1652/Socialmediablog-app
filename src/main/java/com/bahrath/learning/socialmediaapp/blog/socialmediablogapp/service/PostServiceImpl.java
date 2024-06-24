package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model.PostEntity;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService{


    @Autowired
    private PostRepository postRepository;
    @Override
    public List<PostDto> getAllPosts() {
        List<PostEntity> postEntities=postRepository.findAll();
        if(postEntities!=null){
          return  postEntities.stream().map(postEntity->mapEntityToDto(postEntity)).collect(Collectors.toList());
        }
        else{
            return null;
        }
    }

    private PostDto mapEntityToDto(PostEntity postEntity) {
        PostDto postDto=new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setDescription(postEntity.getDescription());
        postDto.setDescription(postEntity.getContent());
        postDto.setDescription(postEntity.getTitle());
        return postDto;
    }

    @Override
    public PostDto getPostById(Long id) {
        return null;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        return null;
    }

    @Override
    public PostDto updatePostById(PostDto postDto,Long id) {
        return null;
    }

    @Override
    public void deletePostById(Long id) {

    }
}
