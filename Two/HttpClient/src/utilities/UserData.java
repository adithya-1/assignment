package  utilities;

import com.google.gson.annotations.SerializedName;

public class UserData {
    @SerializedName("id")
    private final int id;
    @SerializedName("email")
    private final  String email;
    @SerializedName("first_name")
    private  final String firstName;
    @SerializedName("last_name")
    private final String lastName;
    @SerializedName("avatar")
    private final String avatar;

    public UserData(int id, String email, String firstName, String lastName, String avatar) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatar = avatar;
    }

    public void displayData(){
        System.out.println("User data requested is as follows");
        System.out.println("Id:" + this.id);
        System.out.println("Email:" + this.email);
        System.out.println("First Name:" + this.firstName);
        System.out.println("Last Name:" + this.lastName);
        System.out.println("Avatar:" + this.avatar);
        System.out.println("__________________________________________________");

    }
}
