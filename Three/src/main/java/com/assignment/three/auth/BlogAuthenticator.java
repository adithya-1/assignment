package com.assignment.three.auth;

import com.assignment.three.core.User;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.util.Sets;
import lombok.Getter;
import java.util.*;


public class BlogAuthenticator implements
        Authenticator<BasicCredentials, User> {

    private   static final  String GUEST="GUEST";
    private   static final  String USER="USER";
    private   static final  String ADMIN="ADMIN";

    @Getter
    private static  Map<String, Set<String>> USERS_ROLES;
    @Getter
    private static  Map<String,String> VALID_USERS;


    static {
            final Map<String, Set<String>> userRoles = new HashMap<>();
            final Map<String, String> validUser = new HashMap<>();
            userRoles.put("guest", Collections.singleton(GUEST));
            userRoles.put("admin",Sets.of(ADMIN,GUEST,USER));
            validUser.put("guest","12345");
            validUser.put("admin","adithya");
            USERS_ROLES=userRoles;
            VALID_USERS=validUser;
    }



    public  void updateUSERS_ROLES(String key){
        if(!USERS_ROLES.containsKey(key)){
            USERS_ROLES.put(key,Sets.of(GUEST,USER));
        }

    }
    public  void updateVALID_USERS(String key,String value){
        if(!VALID_USERS.containsKey(key)){
            VALID_USERS.put(key, value);
        }

    }

    @Override
    public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (USERS_ROLES.containsKey(credentials.getUsername())
                && VALID_USERS.get(credentials.getUsername()).equals(credentials.getPassword())) {
            return Optional.of(new User(credentials.getUsername(),
                    USERS_ROLES.get(credentials.getUsername()),credentials.getPassword()));
        }
        return Optional.empty();
    }
}