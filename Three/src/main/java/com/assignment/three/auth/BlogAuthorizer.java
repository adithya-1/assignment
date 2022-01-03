package com.assignment.three.auth;

import com.assignment.three.core.User;
import io.dropwizard.auth.Authorizer;

public class BlogAuthorizer implements Authorizer<User> {

    @Override
    public boolean authorize(User user, String role) {
        return user.getRoles() != null && user.getRoles().contains(role);
    }
}
