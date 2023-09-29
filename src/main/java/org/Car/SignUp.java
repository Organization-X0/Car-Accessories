package org.Car;

import org.Data.DataBase;
import org.Data.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp {
    String regexPattern = "^(.+)@(\\S+)$";
    DataBase dataBase=new DataBase();
    public SignUp(){

    }
    public void signUpNow(String fullName,String email,String password,String phone){
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches())
            if(fullName.length()>=3 && fullName.length()<=50)
                if(password.length()>=3 && password.length()<=20)
                    if(phone.length()==10){
                        User user =new User(fullName,email,password,phone);
                        dataBase.addUser(user);
                    }

    }
}
