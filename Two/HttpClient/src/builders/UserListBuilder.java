package builders;

import  utilities.*;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserListBuilder {
    @SerializedName("data")
    private final List<UserData> data;

    public UserListBuilder(List<UserData> data) {
        this.data = data;
    }

    public void displayUserList(){
        if(data.isEmpty()){
            System.out.println("Invalid Input");
        }
        else{
            for(UserData x:data){
                x.displayData();
            }
        }

    }
}
