import java.util.*;

import methods.*;

public class MainClass {
    public static void main(String[] args) {
        while (true){
            int a;
            Scanner sc= new Scanner(System.in);
            System.out.println("Please enter:\n1 - Search\n2 - Pagination\n3- Exit\n");
            try{
                a=Integer.parseInt(String.valueOf(sc.nextInt()));
                switch (a){
                    case 1:{
                        System.out.println("Enter user id you want to search");
                        int id=Integer.parseInt(String.valueOf(sc.nextInt()));
                        Search search=new Search();
                        search.searchMethod(id);
                        break;
                    }
                    case 2:{
                        System.out.println("Enter number of users you want to see per page between 1 to 12");
                        int perPage=sc.nextInt();
                        if(perPage>12 || perPage<1){
                            System.out.println("Invalid input");
                        }
                        else{
                            Pagination pagination=new Pagination(perPage);
                            pagination.choosePageNumber();
                        }

                        break;
                    }
                    case 3:{
                        System.exit(0);
                    }
                    default:{
                        System.out.println("Invalid Input");
                    }
                }
            }catch (Exception e){
                System.out.println("Invalid input");
            }

        }

    }
}
