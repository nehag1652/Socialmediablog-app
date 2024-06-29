package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GenericConfiguration {


    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
