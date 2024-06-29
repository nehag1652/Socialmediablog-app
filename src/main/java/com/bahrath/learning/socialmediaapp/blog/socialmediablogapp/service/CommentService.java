package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service;


import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto getCommentByPostIdAndCommentId(Long postId,Long CommentId) ;

    List<CommentDto> getAllCommentsByPostId(Long postId);

   CommentDto createComment(Long postId,CommentDto commentDto);

   CommentDto updateCommentByPostIdAndCommentId(Long postId, Long commentId, CommentDto commentDto);

   void deleteCommentByCommentId(Long commentId);

   void deleteAllCommentsByPostId(Long postId);
}
