package org.car;

import java.util.Objects;

public class Error {
    private String location= null;
    private String msg;

    public void setError(String locationPara){
        location = Objects.requireNonNullElse(locationPara, "null");
        switch (location){
            case "Start", "AdminDashboard","CustomerDashboard","ProductListing","ManageProducts","ProductCRUD","ViewOrderHistory","ScheduleOfAppointments","InstallationRequests","NotificationCenter","Profile","ViewInstallationHistory","ProductCatalog" -> msg=" Invalid Option! ";
            case "Login" -> msg=" Not Registered! ";
            case "SignUp","SearchProduct","UpdateProduct","ManageCategories","ManageAccounts","AddAppointment" -> msg=" Invalid Data! ";
            case "AddProduct" -> msg=" Failed to add a product! ";
            case "UpdateAccount" -> msg=" Invalid Data! (make sure the phone number is 10 digits) ";
            default -> msg="";
        }
    }
    public void checkAndShow(String location,App myApp){
        if(getLocation()!=null && getLocation().equals(location)){
            myApp.getCli().displayMsg(getMsg(),false);
        }
        setError(null);
    }
    public String getLocation(){
        return location;
    }
    public String getMsg(){
        return msg;
    }
}
