package org.data;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String fullName;
    private final String  email;
    private String password;
    private String phone;
    private  ArrayList<Product> orders;
    private ArrayList<Appointment> installations;
    private ArrayList<String> notifications;
    private int notificationCount;

    public User(String fullName,String email, String password,String phone){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.phone=phone;
        orders=new ArrayList<>();
        installations=new ArrayList<>();
        notifications=new ArrayList<>();
        notificationCount=0;
    }
    public User(){
        this.email=null;
    }

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
    public List<Product> getOrders() {
        return orders;
    }
    public void addInstallations(Appointment appointment) {
        installations.add(appointment);
    }
    public List<Appointment> getInstallations() {
        return installations;
    }

    public List<String> getNotifications() {
        return notifications;
    }
    public void pushNotification(String notification){
        notifications.add(notification);
    }
    public void deleteNotification(int i){
        notifications.remove(i);
    }
    public int getNotificationCount(){
        return notificationCount;
    }
    public void resetNotificationCount(){
        notificationCount=0;
    }
    public void increaseNotificationCount(){
        notificationCount++;
    }
}
