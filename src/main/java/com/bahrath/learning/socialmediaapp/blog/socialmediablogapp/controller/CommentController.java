package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.controller;


import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.CommentDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.repository.CommentRepository;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class CommentController {


    @Autowired
    private CommentService commentService;


    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto){

       CommentDto createdCommentDto= commentService.createComment(postId,commentDto);
       return new ResponseEntity<>(createdCommentDto, HttpStatus.CREATED);
    }


    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> findCommentsByPostId(@PathVariable Long postId){
        List<CommentDto> listCommentsDto=commentService.getAllCommentsByPostId(postId);
        return new ResponseEntity<>(listCommentsDto,HttpStatus.FOUND);
    }


    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> findCommentByPostIdAndCommentId(@PathVariable Long postId,
                                                                      @PathVariable Long commentId){
        CommentDto commentDtoByCommentIdAndPostId=commentService.getCommentByPostIdAndCommentId(postId,commentId);

        return new ResponseEntity<>(commentDtoByCommentIdAndPostId,HttpStatus.FOUND);
    }


    @PutMapping("/update/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateCommentByPostIdAndCommentId(@PathVariable Long postId,@PathVariable Long commentId,
                                                                        @RequestBody CommentDto commentDto){
       CommentDto commentDtoTobeUpdated= commentService.
               updateCommentByPostIdAndCommentId(postId, commentId, commentDto);
       return new ResponseEntity<>(commentDtoTobeUpdated,HttpStatus.OK);

    }


    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<String> deleteCommentByCommentId(@PathVariable Long commentId){
      commentService.deleteCommentByCommentId(commentId);
      return new ResponseEntity<>("Deleted successfully comment resource::"+commentId,HttpStatus.OK);
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deleteAllCommentsByPostId(@PathVariable long postId){
        commentService.deleteAllCommentsByPostId(postId);
        return new ResponseEntity<>("Deleted successfully all comments with PostId:"+postId,HttpStatus.OK);
    }
}
