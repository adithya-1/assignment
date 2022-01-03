package com.assignment.three.generators;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

public class HTTPResponseGenerator {

    private final static Map<String,String> message=new HashMap<>();
    public static Response forbiddenResponse(){
        message.clear();
        message.put("message","Invalid Credentials");
        return Response.status(Response.Status.FORBIDDEN).entity(message).build();
    }

    public static Response successResponse(){
        message.clear();
        message.put("message","Success");
        return Response.ok(message).build();
    }
    public static Response userQueryResponse(Object outPut){
        return Response.ok(outPut).build();
    }
    public static Response notFoundResponse(){
        message.clear();
        message.put("message","Not Found");
        return Response.status(Response.Status.NOT_FOUND).entity(message).build();
    }

    public static Response badRequestResponse(){
        message.clear();
        message.put("status","Bad Request");
        return Response.status(Response.Status.BAD_REQUEST).entity(message).build();
    }
}
