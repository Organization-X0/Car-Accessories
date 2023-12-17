package org.data;

public class Appointment {
    private int id;
    private static int lastId;
    private String email;
    private String productName;
    private String carMake;
    private String date;
    private Time time;

    public Appointment(String email,String productName, String carMake, String date,Time time){
        this.email=email;
        this.productName=productName;
        this.carMake=carMake;
        this.date=date;
        this.time=time;
    }
    public Appointment(){};

    public static int getNextId(){
        return ++lastId;
    }
    public static void setLastId(DataBase myDatabase){
        if(myDatabase.getRequestedAppointmentsList().get(myDatabase.getRequestedAppointmentsList().size()-1).getId()>lastId)
            lastId=myDatabase.getRequestedAppointmentsList().get(myDatabase.getRequestedAppointmentsList().size()-1).getId();
    }
    public static int getLastId(){
        return lastId;
    }
    public static void resetLastId(){
        lastId=0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    };

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
    public Time getTime(){
        return time;
    }
    public String getStringTime(){
        return Time.timeToPrint(this.time);
    }
    public void setTime(Time time){
        this.time=time;
    }
}
