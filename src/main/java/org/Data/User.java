package org.Data;

public class User {
    private String fullName;
    private final String  email;
    private String password;
    private String phone;

    public User(String fullName,String email, String password,String phone){
        this.fullName=fullName;
        this.email=email;
        this.password=password;
        this.phone=phone;
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
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
