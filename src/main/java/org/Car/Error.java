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
            case SIGNUP -> msg=" Invalid Data! ";
        }
    }
    public static State getLocation(){
        return location;
    }
    public static String getMsg(){
        return msg;
    }
}
