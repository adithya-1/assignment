package com.assignment.three.resource;

import com.assignment.three.auth.BlogAuthenticator;
import com.assignment.three.core.Author;
import com.assignment.three.db.AuthorDAO;
import com.assignment.three.services.AuthorService;

import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/blog-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class AuthorResource {
    private final AuthorDAO authorDAO;
    private final BlogAuthenticator blogAuthenticator;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @UnitOfWork
    @RolesAllowed("GUEST")
    @Path("/signup")
    public Response createAuthor(Author author){
        return AuthorService.create(authorDAO,author,blogAuthenticator);
    }


}
