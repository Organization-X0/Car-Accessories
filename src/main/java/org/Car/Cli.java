package org.Car;

import org.Data.Category;
import org.Data.Product;
import org.fusesource.jansi.Ansi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Cli {
    public static int page=1;
    public static int totalPages=1;
//    public static int manageProductsOptions=1;
    public static Ansi errorText(String text){
        return ansi().eraseScreen().fgBright(WHITE).bgBright(RED).a(text).reset();
    }
    public static Ansi blueBgText(String text){
        return ansi().eraseScreen().bg(BLUE).fgBright(WHITE).a(text).reset();
    }
    public static Ansi blueBoldText(String text){
        return ansi().eraseScreen().fgBright(BLUE).a(text).reset();
    }
    public static Ansi blueText(String text){
        return ansi().eraseScreen().fg(BLUE).a(text).reset();
    }
    public static Ansi greenBgText(String text){
        return ansi().eraseScreen().bg(GREEN).fgBright(WHITE).a(text).reset();
    }
    public static Ansi purpleBoldText(String text){
        return ansi().eraseScreen().fgBright(MAGENTA).a(text).reset();
    }
    public static Map<String,String> displayLogin(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String>data=new HashMap<>();
        System.out.println(Cli.blueBgText(" Login "));
        System.out.print(Cli.blueText("Email:"));
//        scanner.nextLine();
        String Email=scanner.nextLine();
        System.out.print(Cli.blueText("Password:"));
        String pass=scanner.nextLine();
        data.put("email",Email);
        data.put("password",pass);
        return data;
    }
    public static Map<String,String> displaySignUp(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data = new HashMap<>();

        System.out.print(Cli.blueBgText(" Sing Up ")+"\n"+Cli.blueText("Full Name: "));
        scanner.nextLine();
        data.put("fullName",scanner.nextLine());
        System.out.print(Cli.blueText("Email: "));
        data.put("email",scanner.nextLine());
        System.out.print(Cli.blueText("Phone: "));
        data.put("phone",scanner.nextLine());
        System.out.print(Cli.blueText("Password: "));
        data.put("password",scanner.nextLine());
        return data;
    }
    public static void displayMain(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome");
        //Still in progress...
        String mainOption=scanner.nextLine();
        System.out.println(mainOption);
    }
    public static String displayAdminDashboard(){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Admin Dashboard:"));
        System.out.println("1. "+Cli.blueText("Manage Products"));
        System.out.println("2. "+Cli.blueText("Manage Categories"));
        System.out.println("3. "+Cli.blueText("Manage User Accounts"));
        System.out.println("4. "+Cli.blueText("Logout"));
        return scanner.nextLine();
    }
    public static String displayManageProducts(ArrayList<Category> categoryArrayList){
        int manageProductsOptions=1;
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Manage Products:"));
        System.out.println((manageProductsOptions++)+". "+Cli.blueText("All Products"));
        System.out.println((manageProductsOptions++)+". "+Cli.blueText("Search for a Product"));
        for(Category category : categoryArrayList){
            System.out.println((manageProductsOptions++)+". "+Cli.blueText(category.getName()+" category"));
        }
        System.out.println(manageProductsOptions+". "+Cli.blueText("Back to Dashboard"));
        return scanner.nextLine();
    }
    public static String displayProducts(ArrayList<Product> productArrayList){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("All Products:"));
        int i=1;

        if(page!=1)
            i=((page-1)*10)+1;

        for (; (i<=productArrayList.size() && i<i+10);i++){
            System.out.println(i+". "+Cli.blueText(productArrayList.get(i-1).getName()+", id:"+productArrayList.get(i-1).getId()));
            if(i%10==0) break;
        }
        totalPages=(int)Math.ceil(productArrayList.size()/10.0);
        System.out.println("page:"+page+"/"+totalPages);
        if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            System.out.println("[ a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");


        return scanner.nextLine();
    }
    public static Map<String,String> displayAddProduct(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(Cli.blueBgText(" ADD PRODUCT "));
        System.out.println(Cli.blueText("Choose category: "));
        System.out.println("""
                1. Interior
                2. Exterior
                3. Electronics
                """);
        String categoryOption = scanner.nextLine();

        String []categoryArray=new String[]{"Interior","Exterior","Electronics"};
        data.put("category",categoryArray[Integer.parseInt(categoryOption)-1]);
        System.out.println(Cli.blueText("Product name: "));
        data.put("name",scanner.nextLine());
        System.out.println(Cli.blueText("Description: "));
        data.put("description",scanner.nextLine());
        System.out.println(Cli.blueText("Price : "));
        data.put("price",scanner.nextLine());
        return data;
    }
    public static void displayMsg(String msg,boolean success){
        Scanner scanner=new Scanner(System.in);
        if(success)
            System.out.println(Cli.greenBgText(msg));
        else
            System.out.println(Cli.errorText(msg));

        System.out.println("[b:back]");
        scanner.nextLine();
    }

    public static String displayStart(){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" Program "));
        System.out.println("1. "+Cli.blueText("Login"));
        System.out.println("2. "+Cli.blueText("Sign Up"));
        System.out.println("3. "+Cli.blueText("Exit"));

        return scanner.nextLine();
    }
}
