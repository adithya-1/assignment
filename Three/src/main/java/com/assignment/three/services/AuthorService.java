package com.assignment.three.services;

import com.assignment.three.auth.BlogAuthenticator;
import com.assignment.three.core.Author;
import com.assignment.three.db.AuthorDAO;
import com.assignment.three.generators.HTTPResponseGenerator;

import javax.ws.rs.core.Response;
import java.util.UUID;

public class AuthorService {
    public  static Response create(AuthorDAO authorDAO, Author author, BlogAuthenticator blogAuthenticator){
        if(author==null || author.getAuthorName()==null){
            return HTTPResponseGenerator.badRequestResponse();
        }
        UUID uuid= UUID.randomUUID();
        String authorId=uuid.toString();
        author.setAuthorId(authorId);
        Author newAuthor=authorDAO.createAuthor(author);
        blogAuthenticator.updateUSERS_ROLES(newAuthor.getAuthorId());
        blogAuthenticator.updateVALID_USERS(newAuthor.getAuthorId(),
                newAuthor.getAuthorId());


        return HTTPResponseGenerator.userQueryResponse(newAuthor);
    }
}
