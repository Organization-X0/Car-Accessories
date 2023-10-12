package org.Car;

public class Error {
    private static State location=State.NO_ERROR;
    private static String msg;

    public static void setError(State locationPara){
        location=locationPara;
        switch (location){
            case NO_ERROR -> msg="";
            case START, ADMIN_DASHBOARD,MANAGE_PRODUCTS,PRODUCTS_CRUD -> msg=" Invalid Option! ";
            case LOGIN -> msg=" Not Registered! ";
            case SIGNUP, SEARCH_PRODUCT, UPDATE_PRODUCT, MANAGE_CATEGORIES, MANAGE_ACCOUNTS-> msg=" Invalid Data! ";
            case ADD_PRODUCT -> msg=" Failed to add a product! ";
            case UPDATE_ACCOUNT -> msg=" Invalid Data! (make sure the phone number is 10 digits) ";
        }
    }
    public static State getLocation(){
        return location;
    }
    public static String getMsg(){
        return msg;
    }
}
