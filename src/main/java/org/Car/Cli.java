package org.Car;

import org.fusesource.jansi.Ansi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Cli {
    private static Scanner scanner=new Scanner(System.in);
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
    public static Ansi purpleBoldText(String text){
        return ansi().eraseScreen().fgBright(MAGENTA).a(text).reset();
    }
    public static Map<String,String> displayLogin(){
        Map<String,String>data=new HashMap<>();
        System.out.println(Cli.blueBgText(" Login "));
        System.out.print(Cli.blueText("Email:"));
        scanner.nextLine();
        String Email=scanner.nextLine();
        System.out.print(Cli.blueText("Password:"));
        String pass=scanner.nextLine();
        data.put("email",Email);
        data.put("password",pass);
        return data;
    }
    public static Map<String,String> displaySignUp(){
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
        System.out.println("Welcome");
        //Still in progress...
        String mainOption=scanner.nextLine();
        System.out.println(mainOption);
    }
    public static int displayAdminDashboard(){
        System.out.println(Cli.blueBgText("Admin Dashboard:"));
        System.out.println(Cli.blueText("""
                1. Manage Products
                2. Manage Categories
                3. Manage User Accounts
                4. Logout"""));
        int option=scanner.nextInt();
        return (option<5) ? option : (0);

    }
    public static int displayStart(){
        System.out.println(Cli.blueBgText(" Program "));
        System.out.println(Cli.blueText("""
                1.Login
                2.Sign Up
                3.Exit
                """));
        int option=scanner.nextInt();
        return (option<4) ? option : (0);
    }
}
