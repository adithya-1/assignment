package  methods;

import builders.*;
import utilities.*;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class Search {
    public void searchMethod(int id){
        try{
            URL url = new URL("https://reqres.in/api/users/"+id);
            try{
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.addRequestProperty("User-Agent", "Mozilla/4.76");
                con.setRequestMethod("GET");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder content = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                con.disconnect();


                Gson gson = new Gson();
                UserDataBuilder userDataBuilder = gson.fromJson(String.valueOf(content), UserDataBuilder.class);
                UserData userData=userDataBuilder.getUser();
                userData.displayData();



            }catch(IOException e){
                System.out.println("Invalid User");
            }

        }catch (MalformedURLException e){
            System.out.println(e.getMessage());
        }
    }
}
