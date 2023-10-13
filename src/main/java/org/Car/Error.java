package org.Car;

public class Error {
    private static StateEnum location= StateEnum.NO_ERROR;
    private static String msg;

    public static void setError(StateEnum locationPara){
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
    public static void checkAndShow(StateEnum location){
        if(Error.getLocation().equals(location)){
            Cli.displayMsg(Error.getMsg(),false);
        }
    }
    public static StateEnum getLocation(){
        return location;
    }
    public static String getMsg(){
        return msg;
    }
}
