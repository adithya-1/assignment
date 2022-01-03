package com.assignment.three.generators;

import com.assignment.three.core.Blog;
import java.util.List;

public class TotalPageValueGenerator {
    public static Integer generate(List<Blog> blogList, Integer perPage){
        Integer totalPage;
        if(blogList.size()%perPage==0){
            totalPage=blogList.size()/perPage;
        }
        else{
            totalPage=blogList.size()/perPage+1;
        }
        return  totalPage;
    }
}
