package com.assignment.two.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String email;
    private String first_name;
    private  String last_name;
    private String avatar;

    @Override
    public String toString(){
        return "User data requested is as follows\n" +
                "Id:"+this.id+"\n"+
                "Email:" + this.email+"\n"+
                "First Name:" + this.first_name+"\n"+
                "Last Name:" + this.last_name+"\n"+
                "Avatar:" + this.avatar+"\n"+
                "__________________________________________________\n";
    }

}
