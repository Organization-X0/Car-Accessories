package org.Car;

import org.Data.DataBase;
import org.Data.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp {
    String regexPattern = "^(.+)@(\\S+)$";
    DataBase myDataBase;
    public SignUp(DataBase dataBase){
       this.myDataBase=dataBase;
    }
    public Map<String,String> displaySignUp(){
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
    public boolean signUpNow(String fullName,String email,String phone,String password){
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            if(fullName.length()>=3 && fullName.length()<=50)
                if(password.length()>=3 && password.length()<=20)
                    if(phone.length()==10){
                        User user =new User(fullName,email,password,phone);
                        myDataBase.addUser(user);
                        return true;
                    }
        return false;
    }
}
