package com.assignment.three.services;

import com.assignment.three.core.Author;
import com.assignment.three.core.Blog;
import com.assignment.three.core.User;
import com.assignment.three.dataTransfer.BlogDataTransfer;
import com.assignment.three.db.BlogDAO;
import com.assignment.three.generators.AuthorsPerPageGenerator;
import com.assignment.three.generators.HTTPResponseGenerator;
import com.assignment.three.generators.TotalPageValueGenerator;
import com.assignment.three.mapper.BlogToDataTransferMapper;


import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.*;

public class BlogService {

    public static Response findById(BlogDAO blogDAO, Integer blogId){
        if(blogId==null){
            return HTTPResponseGenerator.badRequestResponse();
        }
        Optional<Blog> optionalBlog= blogDAO.findBlogByIdDAO(blogId);
        if(optionalBlog.isPresent()){
            return HTTPResponseGenerator.userQueryResponse(BlogToDataTransferMapper.blogToTransferMapperMethod(optionalBlog.get()));
        }else{
            return  HTTPResponseGenerator.notFoundResponse();
        }
    }


    public  static Response pagination(BlogDAO blogDAO,Integer pageNo,Integer perPage){
        if(pageNo==null||perPage==null){
            return HTTPResponseGenerator.badRequestResponse();
        }
        List<Blog> blogList = blogDAO.findAllBlogsDAO();
        if (blogList.size()==0){
            Map<String,String> outPut=new HashMap<String,String>(){{
                put("message","Currently no blogs exist");
            }};
            return HTTPResponseGenerator.userQueryResponse(outPut);
        }

        List<Blog>subList= AuthorsPerPageGenerator.generate(blogList,pageNo,perPage);
        if(subList.size()==0){
            return HTTPResponseGenerator.badRequestResponse();
        }else{
            Integer totalPage= TotalPageValueGenerator.generate(blogList,perPage);
            List<BlogDataTransfer> data=new ArrayList();
            for(Blog b:subList){
                data.add(BlogToDataTransferMapper.blogToTransferMapperMethod(b));
            }
            Map<String,Object> outPut=new HashMap<String,Object>(){{
                put("pageNo",pageNo);
                put("totalPage", totalPage);
                put("data", data);
            }};
            return HTTPResponseGenerator.userQueryResponse(outPut);

        }
    }



    public  static Response create(BlogDAO blogDAO, User user, Blog blog){
        if(blog==null||blog.getBlogId()==null ||
                blog.getBlogName()==null || blog.getBlogContent()==null){
            return HTTPResponseGenerator.badRequestResponse();
        }
        Author author=new Author();
        author.setAuthorId(user.getName());
        blog.setAuthor(author);
        blog.setCreatedAt(LocalDateTime.now());
        Blog newBlog=blogDAO.createBlogDAO(blog);
        Map<String,String> outPut=new HashMap<String,String>(){{
            put("blogId",newBlog.getBlogId().toString());
            put("authorId",newBlog.getAuthor().getAuthorId());
        }};
        return HTTPResponseGenerator.userQueryResponse(outPut);

    }

    public static Response update(BlogDAO blogDAO, Blog newBlog, User user){
        if(newBlog==null || newBlog.getBlogId()==null ||
                newBlog.getBlogName()==null || newBlog.getBlogContent()==null){
            return  HTTPResponseGenerator.badRequestResponse();
        }
        Optional<Blog> optionalBlog= blogDAO.findBlogByIdDAO(newBlog.getBlogId());
        if(!optionalBlog.isPresent()){
            return  HTTPResponseGenerator.notFoundResponse();
        }else{
            Blog oldBlog=optionalBlog.get();
            if(!oldBlog.getAuthor().getAuthorId().equals(user.getName())){
                return  HTTPResponseGenerator.forbiddenResponse();
            }else{
                oldBlog.setBlogContent(newBlog.getBlogContent());
                oldBlog.setBlogName(newBlog.getBlogName());
                return  HTTPResponseGenerator.successResponse();
            }
        }

    }

    public static Response delete(BlogDAO blogDAO, Integer blogId, User user){
        if(blogId==null){
            return  HTTPResponseGenerator.badRequestResponse();
        }
        Optional<Blog> optionalBlog= blogDAO.findBlogByIdDAO(blogId);
        if(!optionalBlog.isPresent()){
            return  HTTPResponseGenerator.notFoundResponse();
        }else{
            Blog blog=optionalBlog.get();
            if(!blog.getAuthor().getAuthorId().equals(user.getName())){
                return  HTTPResponseGenerator.forbiddenResponse();
            }else {
                return blogDAO.deleteBlogDAO(blog);
            }
        }
    }

}
