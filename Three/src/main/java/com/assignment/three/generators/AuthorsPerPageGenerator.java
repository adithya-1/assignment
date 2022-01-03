package com.assignment.three.generators;

import com.assignment.three.core.Blog;

import java.util.ArrayList;
import java.util.List;

public class AuthorsPerPageGenerator {
    public static List<Blog> generate(List<Blog> blogList, Integer pageNo, Integer perPage){

        List<Blog>subList;
        int startIndex=perPage*(pageNo-1);
        try {
            subList=blogList.subList(Math.max(startIndex,0),Math.min(startIndex+perPage,blogList.size()));
            return  subList;
        }catch (Exception e){
            subList=new ArrayList<>();
            return  subList;
        }
    }
}
