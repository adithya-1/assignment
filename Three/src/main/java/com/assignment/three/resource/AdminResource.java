package com.assignment.three.resource;

import com.assignment.three.auth.BlogAuthenticator;
import com.assignment.three.db.AuthorDAO;
import com.assignment.three.services.AdminService;
import io.dropwizard.hibernate.UnitOfWork;
import lombok.AllArgsConstructor;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/blog-api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class AdminResource {
    private final AuthorDAO authorDAO;
    private final BlogAuthenticator blogAuthenticator;

    @GET
    @UnitOfWork
    @RolesAllowed("ADMIN")
    @Path("/admin")
    public Response usersToAuthentication(){
        return AdminService.existingAuthorsToAuthentication(authorDAO,blogAuthenticator);
}
}
