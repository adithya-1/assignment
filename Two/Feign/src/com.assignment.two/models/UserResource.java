package com.assignment.two.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResource {
    private User data;
    @Override
    public String toString(){
        return String.valueOf(this.data);
    }
}
