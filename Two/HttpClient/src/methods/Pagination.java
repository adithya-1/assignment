package methods;

import builders.*;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Pagination {
    private  final int perPage;

    public Pagination(int perPage){
        this.perPage=perPage;
    }

    public void choosePageNumber(){
        while(true){
            Scanner scString= new Scanner(System.in);
            System.out.println
                    ("Enter page number you want to see between 1 to "+
                            Integer.parseInt(String.valueOf(12/this.perPage))+
                            " or type e to exit");
            String pageNumber=scString.nextLine();
            if(pageNumber.equals("e")){
                break;
            }else{
                try{
                    paginationMethod(Integer.parseInt(pageNumber));
                }catch (NumberFormatException nfe) {
                    System.out.println("Invalid Input");
                }

            }

        }

    }
    private void paginationMethod(int pageNumber){
        if(pageNumber>0){
            try{
                URL url = new URL("https://reqres.in/api/users?page="+pageNumber+"&per_page="+this.perPage);
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
                    UserListBuilder userListBuilder = gson.fromJson(String.valueOf(content), UserListBuilder.class);
                    userListBuilder.displayUserList();

                }catch(IOException e){
                    System.out.println("Invalid Input");
                }

            }catch (MalformedURLException e){
                System.out.println(e.getMessage());
            }
        }else{
            System.out.println("Invalid Input");
        }

    }
}
