package org.Car;

import org.Data.DataBase;
import org.Data.User;

import javax.lang.model.type.NullType;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Login {
    DataBase myDatabase;
    public Login(DataBase dataBase){
       this.myDatabase=dataBase;
    }
    public boolean loginNow(String Email,String password){
        User user=myDatabase.search(Email);
        if(user==null){
            return false;
        }
        return user.checkPassword(password);
    }
    public Map<String,String> displayLogin(){
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
}
