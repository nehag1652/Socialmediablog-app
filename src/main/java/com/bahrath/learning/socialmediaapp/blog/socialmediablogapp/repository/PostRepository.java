package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.repository;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository <PostEntity,Long>{



}
