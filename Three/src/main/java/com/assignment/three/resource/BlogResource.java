package com.assignment.three.resource;

import com.assignment.three.services.BlogService;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.assignment.three.core.Blog;
import com.assignment.three.core.User;
import com.assignment.three.db.BlogDAO;



@Path("/blog-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class BlogResource {
    private final BlogDAO blogDAO;

    @GET
    @Path("/{blogId}")
    @UnitOfWork
    @RolesAllowed("GUEST")
    public Response findBlogById(@PathParam("blogId") Integer blogId){
        return  BlogService.findById(blogDAO,blogId);
    }

    @GET
    @UnitOfWork
    @Path("/paginated")
    @RolesAllowed("GUEST")
    public Response paginationMethod(@QueryParam("pageNo")Integer pageNo,@QueryParam("perPage")Integer perPage){
        return BlogService.pagination(blogDAO,pageNo,perPage);
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    @RolesAllowed(value = "USER")
    public Response createBlog(Blog blog, @Auth User user){
        return BlogService.create(blogDAO,user,blog);
    }


    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    @RolesAllowed("USER")
    public Response updateBlog(Blog newBlog, @Auth User user){
        return BlogService.update(blogDAO,newBlog,user);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Timed
    @UnitOfWork
    @RolesAllowed("USER")
    @Path("/{blogId}")
    public  Response  deleteBlog(@PathParam("blogId")Integer blogId,@Auth User user){
        return BlogService.delete(blogDAO,blogId,user);
    }



}
