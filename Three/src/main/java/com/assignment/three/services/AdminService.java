package com.assignment.three.services;

import com.assignment.three.auth.BlogAuthenticator;
import com.assignment.three.core.Author;
import com.assignment.three.db.AuthorDAO;
import com.assignment.three.generators.HTTPResponseGenerator;

import javax.ws.rs.core.Response;
import java.util.List;

public class AdminService {
    public static Response existingAuthorsToAuthentication(AuthorDAO authorDAO, BlogAuthenticator blogAuthenticator){
        List<Author> authorList=authorDAO.findAllAuthor().getResultList();
        for(Author a:authorList){
            blogAuthenticator.updateUSERS_ROLES(a.getAuthorId());
            blogAuthenticator.updateVALID_USERS(a.getAuthorId(),a.getAuthorId());
        }
        return HTTPResponseGenerator.successResponse();
    }
}
