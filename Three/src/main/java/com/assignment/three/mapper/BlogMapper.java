package com.assignment.three.mapper;

import com.assignment.three.core.Blog;
import com.assignment.three.dataTransfer.BlogDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BlogMapper {
   BlogMapper INSTANCE = Mappers.getMapper(BlogMapper.class);


   @Mapping(source="author.authorName",target = "authorName")
    BlogDTO blogToBLogDto(Blog blog);
}
