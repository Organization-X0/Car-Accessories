package org.Car;

import org.Data.DataBase;
import org.Data.User;

import javax.lang.model.type.NullType;

public class Login {

    public Login(){

    }
    public boolean loginNow(String Email,String password){
        DataBase data=new DataBase();
        User user=data.search(Email);
        if(user==null){
            return false;

        }
        return user.checkPassword(password);


    }
}
