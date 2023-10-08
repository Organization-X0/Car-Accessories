package org.Data;

import java.util.Date;

public class Appointment {
    private int id;
    private String email;
    private String productName;
    private String carMake;
    private String date;

    public Appointment(String email,String productName, String carMake, String date){
        this.email=email;
        this.productName=productName;
        this.carMake=carMake;
        this.date=date;
    }
    public Appointment(){};

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
