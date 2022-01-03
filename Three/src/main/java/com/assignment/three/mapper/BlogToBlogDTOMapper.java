package com.assignment.three.mapper;

import com.assignment.three.core.Blog;
import com.assignment.three.dataTransfer.BlogDTO;

public class BlogToBlogDTOMapper {
    public  static BlogDTO mapper(Blog source){
        BlogDTO blogDTO =new BlogDTO(
                source.getBlogId(),source.getBlogName(),source.getBlogContent(),source.getAuthor().getAuthorName()
        );
        return blogDTO;
    }
}
