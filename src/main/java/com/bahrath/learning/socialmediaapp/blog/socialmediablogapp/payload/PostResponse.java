package com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.payload;

import com.bahrath.learning.socialmediaapp.blog.socialmediablogapp.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponse {

    private List<PostDto> content;
    private int pageNo;
    private int pageSize;
    private int totalElements;
    private int totalPage;
    private boolean isLastPage;


}
