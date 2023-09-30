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
}
