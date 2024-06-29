package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="posts")
public class PostEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Long id;

    @Column(name="title")
   private String title;

        @Column(name="description")
   private String description;

    @Column(name="content")
   private String content;

    @OneToMany(mappedBy = "postEntity",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<CommentEntity> comments=new HashSet<>();

}

