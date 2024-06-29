package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.CommentDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.exceptions.ResourceNotFoundException;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model.CommentEntity;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model.PostEntity;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.repository.CommentRepository;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;


    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDto getCommentByPostIdAndCommentId(Long postId, Long commentId) {

        CommentEntity commentEntity = commentRepository.findCommentByPostIdAndCommentId(postId, commentId)
                .orElseThrow(()->new ResourceNotFoundException("comment","commentId",String.valueOf(commentId)));
        if (commentEntity != null) {
            return convertEntityToDto(commentEntity);
        } else {
            return null;
        }
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Long postId) {
        List<CommentEntity> commentEntities = commentRepository.findByPostId(postId);
        if(commentEntities != null && !commentEntities.isEmpty()){
            return commentEntities.stream().map(commentEntity -> convertEntityToDto(commentEntity))
                    .collect(Collectors.toList());
        }
        return null;

    }

    @Override
    public CommentDto createComment(Long postId, CommentDto commentDto) {

        //convert Dto to Entity
        CommentEntity commentEntity = convertDtoToEntity(commentDto);

        //fetch post Based On postId
        PostEntity postEntity = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "Id", String.valueOf(postId)));

        //Set comment to a particular post
        commentEntity.setPostEntity(postEntity);

        //save to db
        CommentEntity savedCommentEntity = commentRepository.save(commentEntity);

        //mapping entity to dto
        CommentDto savedCommentDto = convertEntityToDto(commentEntity);
        return savedCommentDto;


    }


    @Override
    public CommentDto updateCommentByPostIdAndCommentId(Long postId, Long commentId, CommentDto commentDto) {
        CommentEntity commentEntityToBeUpdated = commentRepository.findCommentByPostIdAndCommentId(postId, commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", String.valueOf(commentId)));

        commentEntityToBeUpdated.setUserName(commentDto.getUserName());
        commentEntityToBeUpdated.setEmail(commentDto.getEmail());
        commentEntityToBeUpdated.setBody(commentDto.getBody());
        CommentEntity updatedCommentEntity = commentRepository.save(commentEntityToBeUpdated);
        return convertEntityToDto(updatedCommentEntity);


    }

    @Override
    public void deleteCommentByCommentId(Long commentId) {
        CommentEntity commentEntityToBeDeleted = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", String.valueOf(commentId)));
        commentRepository.delete(commentEntityToBeDeleted);
    }


    @Override
    public void deleteAllCommentsByPostId(Long postId) {

       List<CommentEntity> commentEntityList=commentRepository.findByPostId(postId);
       if(commentEntityList!=null && !commentEntityList.isEmpty()){
           commentRepository.deleteByPostId(postId);
       }

    }


    private CommentEntity convertDtoToEntity(CommentDto commentDto) {

//        CommentEntity commentEntity = new CommentEntity();
//        commentEntity.setUserName(commentDto.getUserName());
//        commentEntity.setEmail(commentDto.getEmail());
//        commentEntity.setBody(commentDto.getBody());

        CommentEntity commentEntity = modelMapper.map(commentDto, CommentEntity.class);
        return commentEntity;
    }

    private CommentDto convertEntityToDto(CommentEntity commentEntity) {
//        CommentDto commentDto = new CommentDto();
//        commentDto.setUserName(commentEntity.getUserName());
//        commentDto.setEmail(commentEntity.getEmail());
//        commentDto.setBody(commentEntity.getBody());

        CommentDto commentDto = modelMapper.map(commentEntity, CommentDto.class);
        return commentDto;
    }

}
