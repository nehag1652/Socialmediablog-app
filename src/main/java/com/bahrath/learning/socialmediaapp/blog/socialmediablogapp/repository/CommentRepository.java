package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.repository;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.CommentDto;
import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model.CommentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@Transactional
public interface CommentRepository extends JpaRepository<CommentEntity,Long> {


   @Query(value="SELECT * FROM comments WHERE post_id=?1",nativeQuery = true)
   List<CommentEntity> findByPostId(Long postId);


   @Query(value="Select * from comments c where c.post_id=?1 and id=?2",nativeQuery = true)
   Optional<CommentEntity> findCommentByPostIdAndCommentId(Long postId, Long CommentId);


   @Modifying
   @Query(value="DELETE FROM comments c WHERE c.post_id=?1",nativeQuery = true)
   void deleteByPostId(long postId);


}
