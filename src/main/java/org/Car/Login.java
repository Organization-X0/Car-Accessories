package org.Car;

import org.Data.DataBase;
import org.Data.User;

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
