package com.assignment.two;

import com.assignment.two.clients.UserClient;
import com.assignment.two.utilities.ClientInput;


public class MainClass {
    public static void main(String[] args) {
        UserControllerFeignClientBuilder builder=new UserControllerFeignClientBuilder();
        UserClient client=builder.getUserClient();
        ClientInput input=new ClientInput();
        input.setClient(client);
        input.takeInput();
    }
}
