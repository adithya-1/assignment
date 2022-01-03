package com.assignment.two;

import com.assignment.two.clients.UserClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.Getter;

@Getter
public class UserControllerFeignClientBuilder {
    private final UserClient userClient = createClient(UserClient.class, "https://reqres.in/api");

    private static <T> T createClient(Class<T> type, String uri) {
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(type, uri);
    }
}