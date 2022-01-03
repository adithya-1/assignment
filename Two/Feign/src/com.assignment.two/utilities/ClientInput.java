package com.assignment.two.utilities;

import com.assignment.two.clients.UserClient;
import com.assignment.two.models.UserListResource;
import com.assignment.two.models.UserResource;
import com.assignment.two.models.User;
import lombok.Setter;

import java.util.Iterator;
import java.util.Scanner;

@Setter
public class ClientInput {
    private UserClient client;

    private void iDInput(){
        final Scanner sc=new Scanner(System.in);
        System.out.println("Enter user id you want to search");
        String id = sc.nextLine();
        UserResource user = this.client.search(id);
        System.out.println(user);
    }
    private void perPageInput(){
        final Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of users you want to see per page between 1 to 12");
        String perPage = sc.nextLine();
        if (Integer.parseInt(perPage) > 12 || Integer.parseInt(perPage) < 1) {
            System.out.println("Invalid input");
        } else {
            pageInput(Integer.parseInt(perPage));
        }

    }
    private void pageInput(int perPage){

        while(true){
            final Scanner sc=new Scanner(System.in);
            System.out.println
                    ("Enter page number you want to see between 1 to "+
                            Integer.parseInt(String.valueOf(12/perPage))+
                            " or type e to exit");
            String pageNumber=sc.nextLine();
            if(pageNumber.equals("e")){
                break;
            }else{
                try{
                    if(Integer.parseInt(pageNumber)>0) {
                        UserListResource userList = this.client.pagination(pageNumber, String.valueOf(perPage));
                        Iterator<User> userIterator=userList.getData().iterator();
                        while (userIterator.hasNext()){
                            System.out.println(userIterator.next());
                        }
                    }else{
                        System.out.println("Invalid Input");
                    }
                }catch (NumberFormatException nfe) {
                    System.out.println("Invalid Input");
                }

            }

        }

    }

    public void takeInput() {
        while (true) {
            int initialInput;
            final Scanner sc=new Scanner(System.in);
            System.out.println("Please enter:\n1 - Search\n2 - Pagination\n3- Exit\n");
            try {
                initialInput = Integer.parseInt(String.valueOf(sc.nextInt()));
                switch (initialInput) {
                    case 1: {
                        iDInput();
                        break;
                    }
                    case 2: {
                        perPageInput();
                        break;
                    }
                    case 3: {
                        System.exit(0);
                    }
                    default: {
                        System.out.println("Invalid Input");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input");
            }

        }
    }
}
