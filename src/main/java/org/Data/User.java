package org.Data;

import java.util.ArrayList;

public class User {
    private String fullName;
    private final String  email;
    private String password;
    private String phone;
    private  ArrayList<Product> orders;

    public User(String fullName,String email, String password,String phone){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.phone=phone;
        orders=new ArrayList<Product>();
    }
    public User(){
        this.email=null;
    };

    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public void addOrder(Product product) {
        orders.add(product);
    }
    public ArrayList<Product> getOrders() {
        return orders;
    }
}
