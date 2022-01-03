package com.assignment.three.core;
import java.security.Principal;


import java.util.Set;

public class User implements Principal {


    private final String name;

    private final Set<String> roles;

    private  final  String password;



    public User(String name, Set<String> roles,String password) {
        this.name = name;
        this.roles = roles;
        this.password=password;
    }

    public String getName() {
        return name;
    }



    public Set<String> getRoles() {
        return roles;
    }


}
