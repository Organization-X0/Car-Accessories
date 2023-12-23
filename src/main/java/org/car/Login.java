package org.car;

import org.data.DataBase;
import org.data.User;

public class Login {
    DataBase myDatabase;
    public Login(DataBase dataBase){
       this.myDatabase=dataBase;
    }
    public boolean loginNow(String email,String password){
        User user=myDatabase.searchAccount(email);
        if(user==null){
            return false;
        }
        return user.checkPassword(password);
    }
}
