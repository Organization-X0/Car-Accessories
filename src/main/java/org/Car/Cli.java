package org.Car;

import org.fusesource.jansi.Ansi;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

public class Cli {
    public static Ansi errorText(String text){
        return ansi().eraseScreen().fgBright(WHITE).bgBright(RED).a(text).reset();
    }
    public static Ansi blueText(String text){
        return ansi().eraseScreen().fg(BLUE).a(text).reset();
    }
    public static Ansi blueBoldText(String text){
        return ansi().eraseScreen().fgBright(BLUE).a(text).reset();
    }
    public static Ansi purpleBoldText(String text){
        return ansi().eraseScreen().fgBright(MAGENTA).a(text).reset();
    }
    public static Map<String,String> displayLogin(){


        Map<String,String>data=new HashMap<>();

        System.out.println(Cli.purpleBoldText("Login"));
        System.out.print(Cli.blueBoldText("Email:"));
        Scanner scanner=new Scanner(System.in);
        String Email=scanner.nextLine();
        System.out.print(Cli.blueBoldText("Password:"));
        String pass=scanner.nextLine();
        data.put("email",Email);
        data.put("password",pass);
        return data;
    }
    public static Map<String,String> displaySignUp(){
        Map<String,String> data = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print(Cli.purpleBoldText("Sing Up")+"\n"+Cli.blueBoldText("Full Name: "));
        data.put("fullname",scanner.nextLine());
        System.out.print(Cli.blueBoldText("Email: "));
        data.put("email",scanner.nextLine());
        System.out.print(Cli.blueBoldText("Phone: "));
        data.put("phone",scanner.nextLine());
        System.out.print(Cli.blueBoldText("Password: "));
        data.put("password",scanner.nextLine());
        return data;
    }
    public static void displayMain(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome");
        String mainOption=scanner.nextLine();
    }
    public static String displayStart(){
        Scanner scanner=new Scanner(System.in);

        System.out.println(Cli.purpleBoldText("Program"));
        System.out.println(Cli.blueBoldText("1.Login"));
        System.out.println(Cli.blueBoldText("2.Sign UP"));
        String option=scanner.nextLine();
        if(option.equals("1")) {
            return "1";
        }else if(option.equals("2")){
            return "2";
        }else{
            return "0";
        }
    }
//    public static void displayError(String str){
//        System.out.println(errorText(str));
//    }
}
