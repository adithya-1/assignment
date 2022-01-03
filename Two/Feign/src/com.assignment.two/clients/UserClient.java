package com.assignment.two.clients;

import com.assignment.two.models.UserListResource;
import com.assignment.two.models.UserResource;

import feign.Param;
import feign.RequestLine;

public interface UserClient {
    @RequestLine("GET /users/{id}")
    UserResource search(@Param("id") String id);
    @RequestLine("GET /users?page={page}&per_page={perPage}")
    UserListResource pagination(@Param(value ="page")String page, @Param(value ="perPage") String perPage);


}
