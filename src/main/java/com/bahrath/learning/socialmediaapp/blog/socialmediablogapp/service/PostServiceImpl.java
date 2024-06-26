package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.exceptions.ResourceNotFoundException;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model.PostEntity;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.payload.PostResponse;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {


    @Autowired
    private PostRepository postRepository;



//    @Override
//    public PostResponse getAllPosts(int pageNo,int pageSize) {
//       Pageable pageable= PageRequest.of(pageNo,pageSize);
//        Page<PostEntity> postEntities = postRepository.findAll(pageable);
//
//        if (postEntities != null) {
//            List<PostDto> postDtoList=postEntities.stream().map(postEntity -> mapEntityToDto(postEntity))
//                    .collect(Collectors.toList());
//
//            PostResponse postResponse=PostResponse.builder().content(postDtoList)
//                    .pageNo(postEntities.getNumber())
//                    .pageSize(postEntities.getSize())
//                    .totalPage(postEntities.getTotalPages())
//                    .totalElements((int) postEntities.getTotalElements())
//                    .isLastPage(postEntities.isLast()).build();
//            return postResponse;
//        }
//
//        return null;
//
//    }

    @Override
    public PostResponse getAllPosts(int pageNo,int pageSize,String sortBy,String sortDirection) {
        Pageable pageable=null;

        if(sortBy!=null && sortDirection!=null){
            Sort sort=sortDirection.equalsIgnoreCase("Asc")? Sort.by(sortBy).ascending():
                    Sort.by(sortBy).descending();
         pageable= PageRequest.of(pageNo,pageSize,sort);
        }
        else{
            pageable= PageRequest.of(pageNo,pageSize);
        }
        Page<PostEntity> postEntities = postRepository.findAll(pageable);

        if (postEntities != null) {
            List<PostDto> postDtoList=postEntities.stream().map(postEntity -> mapEntityToDto(postEntity))
                    .collect(Collectors.toList());

            PostResponse postResponse=PostResponse.builder().content(postDtoList)
                    .pageNo(postEntities.getNumber())
                    .pageSize(postEntities.getSize())
                    .totalPage(postEntities.getTotalPages())
                    .totalElements((int) postEntities.getTotalElements())
                    .isLastPage(postEntities.isLast()).build();
            return postResponse;
        }

        return null;

    }


    @Override
    public PostDto getPostById(Long id) {
        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        return mapEntityToDto(postEntity);
    }


    @Override
    public PostDto createPost(PostDto postDto) {
        PostEntity savedPostEntity = mapDtoToEntity(postDto);
        postRepository.save(savedPostEntity);
        return mapEntityToDto(savedPostEntity);
    }


    @Override
    public PostDto updatePostById (Long id,PostDto postDto) {
        PostEntity postEntityToBeUpdated = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        postEntityToBeUpdated.setTitle(postDto.getTitle());
        postEntityToBeUpdated.setDescription(postDto.getDescription());
        postEntityToBeUpdated.setContent(postDto.getContent());
        PostEntity updatedPostEntity = postRepository.save(postEntityToBeUpdated);
        PostDto updatedPostDto = mapEntityToDto(updatedPostEntity);
        return updatedPostDto;


    }


    @Override
    public void deletePostById(Long id) {

        PostEntity postEntity = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", String.valueOf(id)));
        postRepository.delete(postEntity);


    }


    //Entity to Dto mapper
    private PostDto mapEntityToDto(PostEntity postEntity) {
        PostDto postDto = new PostDto();
        postDto.setId(postEntity.getId());
        postDto.setDescription(postEntity.getDescription());
        postDto.setContent(postEntity.getContent());
        postDto.setTitle(postEntity.getTitle());
        return postDto;
    }

    //Dto to Entity mapper
    private PostEntity mapDtoToEntity(PostDto postDto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setDescription(postDto.getDescription());
        postEntity.setContent(postDto.getContent());
        postEntity.setTitle(postDto.getTitle());
        return postEntity;

    }
}
