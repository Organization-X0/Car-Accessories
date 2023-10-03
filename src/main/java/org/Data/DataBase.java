package org.Data;

import java.util.ArrayList;

public class DataBase {
    private final ArrayList<User> usersList;
    private final ArrayList<Product> productsList;
    public DataBase(){
        usersList=new ArrayList<User>();
        usersList.add(new User("user1","user1@gmail.com","u123","0599123456"));
        usersList.add(new User("user2","user2@gmail.com","u123","0599123456"));
        usersList.add(new User("user3","user3@gmail.com","u123","0599123456"));

        productsList=new ArrayList<Product>();
        productsList.add(new Product("1","Item1","Interior","don't buy please.",3.3,true));
        productsList.add(new Product("2","Item2","Exterior","don't buy please.",2.2,false));
        productsList.add(new Product("3","Item3","Electronics","don't buy please.",5.3,true));
    }
    public User search(String email){
        return usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    public void addUser(User user){
        usersList.add(user);
    }
    public void addProduct(Product product){
        productsList.add(product);
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }
}
