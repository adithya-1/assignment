package com.assignment.three.dataTransfer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BlogDTO {
    private Integer blogId;
    private  String blogName;
    private String blogContent;
    private  String authorName;

}
