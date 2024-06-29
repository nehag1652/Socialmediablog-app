package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="comments")
public class CommentEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="userName")
    private String userName;

    @Column(name="email")
    private String email;

    @Column(name="body")
    private String body;


    //Multiple Comments can belong to single post
    //Mapping b/w CommentEntity and PostEntity
    @ManyToOne
    @JoinColumn(name="post_id",nullable = false)
    private PostEntity postEntity;

}
