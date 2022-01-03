package com.assignment.three.db;

import com.assignment.three.core.Author;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import javax.persistence.Query;


public class AuthorDAO extends AbstractDAO<Author> {
    public AuthorDAO(SessionFactory sessionFactory){
        super(sessionFactory);
    }
    public  Query findAllAuthor(){
        return namedQuery("com.assignment.three.core.Author.findAllAuthor");
    }
    public Author createAuthor(Author author){
       return persist(author);
    }


}
