package com.assignment.three.mapper;

import com.assignment.three.core.Blog;
import com.assignment.three.dataTransfer.BlogDataTransfer;

public class BlogToDataTransferMapper {
    public  static BlogDataTransfer blogToTransferMapperMethod(Blog source){
        BlogDataTransfer blogDataTransfer=new BlogDataTransfer(
                source.getBlogId(),source.getBlogName(),source.getBlogContent(),source.getAuthor().getAuthorName()
        );
        return  blogDataTransfer;
    }
}
