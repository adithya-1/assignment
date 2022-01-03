package builders;

import  utilities.*;

import com.google.gson.annotations.SerializedName;

public class UserDataBuilder {
    @SerializedName("data")
    private final UserData data;

    public UserDataBuilder(UserData data) {
        this.data = data;
    }

    public UserData getUser(){
        return this.data;
    }
}
