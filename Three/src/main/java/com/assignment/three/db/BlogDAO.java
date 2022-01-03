package com.assignment.three.db;

import com.assignment.three.core.Blog;

import com.assignment.three.generators.HTTPResponseGenerator;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;


import javax.ws.rs.core.Response;
import java.util.*;


public class BlogDAO extends AbstractDAO<Blog> {

    public BlogDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public Optional<Blog> findBlogByIdDAO(Integer blogId){
        return Optional.ofNullable(get(blogId));
    }

    public Blog createBlogDAO(Blog blog){
        return persist(blog);

    }

    public Response deleteBlogDAO(Blog blog){
        try{
            currentSession().delete(blog);
            return HTTPResponseGenerator.successResponse();
        }catch (Exception e){
            return HTTPResponseGenerator.forbiddenResponse();
        }

    }
    public  List findAllBlogsDAO(){
        List blogList=namedQuery("com.assignment.three.core.Blog.findAllBlogs").getResultList();
        return  blogList;

    }

}
