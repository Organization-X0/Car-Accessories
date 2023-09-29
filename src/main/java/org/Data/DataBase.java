package org.Data;

import java.util.ArrayList;

public class DataBase {
    private ArrayList<User> usersList;
    public DataBase(){
        usersList=new ArrayList<User>();
        usersList.add(new User("user1","user1@gmail.com","u123"));
        usersList.add(new User("user2","user2@gmail.com","u123"));
        usersList.add(new User("user3","user3@gmail.com","u123"));
    }
    public User search(String email){
        return usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
