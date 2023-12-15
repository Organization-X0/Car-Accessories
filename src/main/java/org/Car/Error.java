package org.Car;

import java.util.Objects;

public class Error {
    private static String location= null;
    private static String msg;

    public static void setError(String locationPara){
        location = Objects.requireNonNullElse(locationPara, "null");
        switch (location){
            case "Start", "AdminDashboard","CustomerDashboard","ProductListing","ManageProducts","ProductCRUD","ViewOrderHistory","ScheduleOfAppointments","InstallationRequests","NotificationCenter","Profile","ViewInstallationHistory","ProductCatalog" -> msg=" Invalid Option! ";
            case "Login" -> msg=" Not Registered! ";
            case "SignUp","SearchProduct","UpdateProduct","ManageCategories","ManageAccounts","AddAppointment" -> msg=" Invalid Data! ";
            case "AddProduct" -> msg=" Failed to add a product! ";
            case "UpdateAccount" -> msg=" Invalid Data! (make sure the phone number is 10 digits) ";
            case "null" -> msg="";
        }
    }
    public static void checkAndShow(String location){
        if(Error.getLocation()!=null && Error.getLocation().equals(location)){
            Cli.displayMsg(Error.getMsg(),false);
        }
    }
    public static String getLocation(){
        return location;
    }
    public static String getMsg(){
        return msg;
    }
}
