package org.car;

import org.data.*;

import java.util.*;
import java.util.stream.IntStream;

public class Cli {

    public static final String EMAIL = "email";
    public static final String LOG_OUT = "Log out";
    public static final String CATEGORY = "category";
    public static final String CATEGORY_TO_PRINT = " | Category:";
    public static final String PRICE_TO_PRINT = " | Price:";
    public static final String PAGE_TO_PRINT = "page:";
    public static final String B_BACK = "[ b:back ]";
    public static final String FULL_CRUD = "[ a:add | d<int>:delete | u<int>:update | b:back ]";
    public static final String PRODUCT_NAME = "Product name: ";
    public static final String IF_U_WANT_UPDATE = "If you don't want to update specific field just press enter.";
    public static final String CAR_MAKE = "Car Make: ";
    public static final String YYYY_MM_DD_D = "YYYY-MM-DD/D: ";
    public static final String PRODUCT_NAME_DATA = "productName";
    public static final String CAR_MAKE_DATA = "carMake";
    public static final String ESCAPE_ANSI = "\033[0m";
    private int page=1;
    private int totalPages=1;
    private final MyLogger myLogger;
    public Cli(){
        myLogger=new MyLogger();
    }
    public static String errorText(String text){
        return "\033[41m\033[97m" + text + ESCAPE_ANSI;
    }
    public static String blueBgText(String text){
        return "\033[44m\033[97m"+text+ ESCAPE_ANSI;
    }
    public static String blueText(String text){
        return "\033[34m" + text + ESCAPE_ANSI;
    }
    public static String greenBgText(String text){
        return "\033[42m\033[97m" + text + ESCAPE_ANSI;
    }

    public void nextPage(){
        page++;
    }
    public void prevPage(){
        page--;
    }
    public int getCurrentPage(){
        return page;
    }
    public void resetPage(){
        page=1;
    }
    public void setTotalPages(int n){
        totalPages=n;
    }
    public int getTotalPages(){
        return totalPages;
    }
    public void setPage(int n){
        page=n;
    }
    public Map<String,String> displayLogin(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String>data=new HashMap<>();
        myLogger.log(blueBgText(" Login "));
        myLogger.log(blueText("Email:"));
        String email=scanner.nextLine();
        myLogger.log(blueText("Password:"));
        String pass=scanner.nextLine();
        data.put(EMAIL,email);
        data.put("password",pass);
        return data;
    }
    public Map<String,String> displaySignUp(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data = new HashMap<>();

        myLogger.log(blueBgText(" Sing Up ")+"\n"+ blueText("Full Name: "));
        scanner.nextLine();
        data.put("fullName",scanner.nextLine());
        myLogger.log(blueText("Email: "));
        data.put(EMAIL,scanner.nextLine());
        myLogger.log(blueText("Phone: "));
        data.put("phone",scanner.nextLine());
        myLogger.log(blueText("Password: "));
        data.put("password",scanner.nextLine());
        return data;
    }
    public String displayAdminDashboard(){
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("Admin Dashboard:"));
        myLogger.log("1. "+ blueText("Manage Products"));
        myLogger.log("2. "+ blueText("Manage Categories"));
        myLogger.log("3. "+ blueText("Manage User Accounts"));
        myLogger.log("4. "+ blueText("Manage Installation Appointments."));
        myLogger.log("5. "+ blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public String displayManageProducts(List<Category> categoryArrayList){
        int manageProductsOptions=1;
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText(" Product Menu "));
        myLogger.log((manageProductsOptions++)+". "+ blueText("All Products"));
        myLogger.log((manageProductsOptions++)+". "+ blueText("Search for a Product"));
        for(Category category : categoryArrayList){
            myLogger.log((manageProductsOptions++)+". "+ blueText(category.getName()+ " " + CATEGORY));
        }
        myLogger.log(manageProductsOptions+". "+ blueText("Back to Dashboard"));
        return scanner.nextLine();
    }

    public String displayCustomerProducts(List<Product> productArrayList){
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("All Products:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, productArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " + blueText(
                productArrayList.get(i).getName() +
                        CATEGORY_TO_PRINT +
                        productArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                        productArrayList.get(i).getPrice() + " | Description:"+productArrayList.get(i).getDescription() +
                        " | Available:"+productArrayList.get(i).isAvailability()
        )));

        totalPages=(int)Math.ceil(productArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            myLogger.log(B_BACK);
        } else if(totalPages==1){
            myLogger.log("[ f<int>:buy product |b:back ]");
        } else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | f<int>:buy product | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | f<int>:buy product | b:back ]");
        else if(page==totalPages)
            myLogger.log("[ p:prev page | f<int>:buy product | b:back ]");

        return scanner.nextLine();
    }
    public String displayProducts(List<Product> productArrayList){
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("All Products:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, productArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " + blueText(
                productArrayList.get(i).getName() +
                        CATEGORY_TO_PRINT +
                        productArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                        productArrayList.get(i).getPrice()+ " | Description:"+productArrayList.get(i).getDescription() +
                        " | Available:"+productArrayList.get(i).isAvailability()
        )));

        totalPages=(int)Math.ceil(productArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            myLogger.log("[ a:add | b:back ]");
        } else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            myLogger.log(FULL_CRUD);
        else if(page==totalPages)
            myLogger.log("[ p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }

    public Map<String,String> displayAddProduct(List<Category> categoryArrayList){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        myLogger.log(blueBgText(" ADD PRODUCT "));

        myLogger.log(blueText("Choose " + CATEGORY + ": "));
        int i=1;
        for(Category category : categoryArrayList){
            myLogger.log((i++)+". "+ blueText(category.getName()+ " " + CATEGORY));
        }

        String categoryOption = scanner.nextLine();

        data.put(CATEGORY,categoryOption);
        myLogger.log(blueText(PRODUCT_NAME));
        data.put("name",scanner.nextLine());
        myLogger.log(blueText("Description: "));
        data.put("description",scanner.nextLine());
        myLogger.log(blueText("Price : "));
        data.put("price",scanner.nextLine());
        return data;
    }
    public String displaySearchProduct(){
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("SEARCH"));
        myLogger.log(blueText("Product name:"));
        return scanner.nextLine();
    }
    public Map<String,String> displayUpdateProduct(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        myLogger.log(blueBgText(" UPDATE PRODUCT "));
        myLogger.log(IF_U_WANT_UPDATE);

        myLogger.log(blueText(PRODUCT_NAME));
        data.put("name",scanner.nextLine());
        myLogger.log(blueText("Description: "));
        data.put("description",scanner.nextLine());
        myLogger.log(blueText("Price : "));
        data.put("price",scanner.nextLine());
        return data;
    }
    public String displayManageCategories(List<Category> categoryArrayList){
        Scanner scanner=new Scanner(System.in);
        int i=1;
        myLogger.log(blueBgText("CATEGORIES"));
        for(Category category : categoryArrayList){
            myLogger.log((i++)+". "+ blueText(category.getName()+ " " + CATEGORY));
        }
        myLogger.log(FULL_CRUD);
        return scanner.nextLine();
    }
    public String displayAddCategory() {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText(" ADD CATEGORY "));

        myLogger.log(blueText("Category name: "));
        return scanner.nextLine();
    }
    public String displayManageAccounts(List<User> userArrayList) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText(" MANAGE ACCOUNTS "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, userArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i+1)+". "+ blueText(userArrayList.get(i).getFullName()+", "+userArrayList.get(i).getEmail()+", "+userArrayList.get(i).getPhone())));

        totalPages=(int)Math.ceil(userArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            myLogger.log(B_BACK);
        } else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            myLogger.log("[ d<int>:delete | u<int>:update | b:back ]");
        else if(page==totalPages)
            myLogger.log("[ p:prev page | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }
    public Map<String, String> displayUpdateAccount() {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        myLogger.log(blueBgText(" UPDATE ACCOUNT "));
        myLogger.log(IF_U_WANT_UPDATE);
        myLogger.log(blueText("Full Name: "));
        data.put("fullName", scanner.nextLine());
        myLogger.log(blueText("Phone: "));
        data.put("phone", scanner.nextLine());
        return data;
    }
    public String displayUpdateCategory() {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText(" UPDATE CATEGORY "));
        myLogger.log("If you don't want to update name just press enter.");

        myLogger.log(blueText("Category name: "));
        return scanner.nextLine();
    }
    public String  displayInstallationAppointments(List<Appointment> appointmentArrayList){
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("Manage Installation Appointments:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, appointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " + blueText(appointmentArrayList.get(i).getEmail()+" | "+
                appointmentArrayList.get(i).getDate()+" | "+
                appointmentArrayList.get(i).getProductName()+" | "+
                appointmentArrayList.get(i).getCarMake()+" | "+
                appointmentArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(appointmentArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            myLogger.log("[ a:add | b:back ]");
        } else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            myLogger.log(FULL_CRUD);
        else if(page==totalPages)
            myLogger.log("[ p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }

    private ArrayList<Time> getAvailableTimes(App myApp,String date){

        ArrayList<Time> availableTimes=new ArrayList<>();
        ArrayList<Appointment> appointmentsWithThisDate = (ArrayList<Appointment>) myApp.myDatabase.searchAppointmentsByDate(date);
        boolean flag=false;
        for (Time time : Time.values()){
            flag=false;
            for (Appointment appointment : appointmentsWithThisDate) {
                if (time == appointment.getTime()) {
                    flag = true;
                    break;
                }
            }
            if(!flag) availableTimes.add(time);
        }
        return availableTimes;
    }
    public Map<String, String> displayAddAppointment(App myApp) {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        myLogger.log(blueBgText(" ADD APPOINTMENT "));

        myLogger.log(blueText("Email of user: "));
        data.put(EMAIL,scanner.nextLine());
        myLogger.log(blueText(PRODUCT_NAME));
        data.put(PRODUCT_NAME_DATA,scanner.nextLine());
        myLogger.log(blueText(CAR_MAKE));
        data.put(CAR_MAKE_DATA,scanner.nextLine());
        myLogger.log(blueText("Date"));
        myLogger.log(blueText(YYYY_MM_DD_D));
        data.put("date",scanner.nextLine());

        int i=0;
        myApp.setAvailableTimes(getAvailableTimes(myApp,data.get("date")));
        for(Time time:myApp.getAvailableTimes()){
            i++;
            myLogger.log(blueText(i+". ")+Time.timeToPrint(time));
        }
        data.put("time",scanner.nextLine());
        return data;
    }

    public Map<String, String> displayAddAppointmentCustomer(App myApp) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> data = new HashMap<>();
        myLogger.log(blueBgText(" ADD APPOINTMENT "));

        data.put(EMAIL, myApp.getEmail());
        myLogger.log(blueText(PRODUCT_NAME));
        data.put(PRODUCT_NAME_DATA, scanner.nextLine());
        myLogger.log(blueText(CAR_MAKE));
        data.put(CAR_MAKE_DATA, scanner.nextLine());
        myLogger.log(blueText("Date"));
        myLogger.log(blueText(YYYY_MM_DD_D));
        data.put("date", scanner.nextLine());

        int i=0;
        myApp.setAvailableTimes(getAvailableTimes(myApp,data.get("date")));
        for(Time time:myApp.getAvailableTimes()){
            i++;
            myLogger.log(blueText(i+". ")+Time.timeToPrint(time));
        }
        data.put("time",scanner.nextLine());
        return data;
    }
    public Map<String, String> displayUpdateAppointment() {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        myLogger.log(blueBgText(" APPOINTMENT PRODUCT "));
        myLogger.log(IF_U_WANT_UPDATE);

        myLogger.log(blueText("Email of user: "));
        data.put(EMAIL,scanner.nextLine());
        myLogger.log(blueText(PRODUCT_NAME));
        data.put(PRODUCT_NAME_DATA,scanner.nextLine());
        myLogger.log(blueText(CAR_MAKE));
        data.put(CAR_MAKE_DATA,scanner.nextLine());
        myLogger.log(blueText("Date"));
        myLogger.log(blueText(YYYY_MM_DD_D));
        data.put("date",scanner.nextLine());
        return data;
    }

    public String displayCustomerDashboard(User account) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("Customer Dashboard:"));
        myLogger.log("1. "+ blueText("Product Catalog"));
        myLogger.log("2. "+ blueText("Request services"));
        myLogger.log("3. "+ blueText("Profile"));
        myLogger.log("4. "+ blueText("Notifications:")+ blueBgText(" "+account.getNotificationCount()+" "));
        myLogger.log("5. "+ blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public String displayProfile(String name, String email, String phone) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("Profile:"));
        myLogger.log(blueText("Name: ")+name);
        myLogger.log(blueText("email: ")+email);
        myLogger.log(blueText("phone: ")+phone);
        myLogger.log("=================================");
        myLogger.log("1. "+ blueText("Edit Profile"));
        myLogger.log("2. "+ blueText("View Order History"));
        myLogger.log("3. "+ blueText("View Installation Requests History"));
        myLogger.log("4. "+ blueText("Back to Customer Dashboard"));
        return scanner.nextLine();
    }

    public String displayOrderHistory(List<Product> ordersArrayList) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("Order History:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, ordersArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " + blueText(
                ordersArrayList.get(i).getName() +
                        CATEGORY_TO_PRINT +
                        ordersArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                        ordersArrayList.get(i).getPrice()
        )));

        totalPages=(int)Math.ceil(ordersArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0 || totalPages==1){
            myLogger.log(B_BACK);
        }else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | b:back ]");
        else if(page==totalPages)
            myLogger.log("[ p:prev page | b:back ]");

        return scanner.nextLine();
    }
    public String displayInstallationHistory(List<Appointment> installationsArrayList ) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("Installation Requests History:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, installationsArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " + blueText(
                installationsArrayList.get(i).getDate()+" | "+
                        installationsArrayList.get(i).getProductName()+" | "+
                        installationsArrayList.get(i).getCarMake()+" | "+
                        installationsArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(installationsArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0 || totalPages==1){
            myLogger.log(B_BACK);
        }else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | b:back ]");
        else if(page==totalPages)
            myLogger.log("[ p:prev page | b:back ]");

        return scanner.nextLine();
    }

    public String displayInstallerDashboard(User account) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText("Installer Dashboard:"));
        myLogger.log("1. "+ blueText("Schedule of Appointments"));
        myLogger.log("2. "+ blueText("Installation Requests"));
        myLogger.log("3. "+ blueText("Notifications:")+ blueBgText(" "+account.getNotificationCount()+" "));
        myLogger.log("4. "+ blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public String displayInstallationRequests(List<Appointment> appointmentArrayList) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText(" Installation Requests "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, appointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " + blueText(appointmentArrayList.get(i).getEmail()+" | "+
                appointmentArrayList.get(i).getDate()+" | "+
                appointmentArrayList.get(i).getProductName()+" | "+
                appointmentArrayList.get(i).getCarMake()+" | "+
                appointmentArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(appointmentArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            myLogger.log(B_BACK);
        } else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | c<int>:confirm | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | c<int>:confirm | b:back ]");
        else if(totalPages==1)
            myLogger.log("[ c<int>:confirm | b:back ]");
        else if(page==totalPages)
            myLogger.log("[ p:prev page | c<int>:confirm | b:back ]");

        return scanner.nextLine();
    }
     public String displayScheduleOfAppointments(List<Appointment> approvedAppointmentArrayList) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText(" Schedule Of Appointments "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, approvedAppointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " + blueText(approvedAppointmentArrayList.get(i).getEmail()+" | "+
                approvedAppointmentArrayList.get(i).getDate()+" | "+
                approvedAppointmentArrayList.get(i).getProductName()+" | "+
                approvedAppointmentArrayList.get(i).getCarMake()+" | "+
                approvedAppointmentArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(approvedAppointmentArrayList.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            myLogger.log(B_BACK);
        } else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | d<int>:done | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | d<int>:done | b:back ]");
        else if(totalPages==1)
            myLogger.log("[ d<int>:done | b:back ]");
        else if(page==totalPages)
            myLogger.log("[ p:prev page | d<int>:done | b:back ]");

        return scanner.nextLine();
    }
    public String displayNotificationCenter(List<String> notifications) {
        Scanner scanner=new Scanner(System.in);
        myLogger.log(blueBgText(" Notification Center "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, notifications.size());

        IntStream.range(start, end).forEach(i -> myLogger.log((i + 1) + ". " +notifications.get(i)));

        totalPages=(int)Math.ceil(notifications.size()/10.0);
        myLogger.log(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            myLogger.log(B_BACK);
        } else if(page<totalPages && page>1)
            myLogger.log("[ n:next page | p:prev page | d<int>:done | b:back ]");
        else if (page<totalPages && page==1)
            myLogger.log("[ n:next page | d<int>:done | b:back ]");
        else if(totalPages==1)
            myLogger.log("[ d<int>:done | b:back ]");
        else if(page==totalPages)
            myLogger.log("[ p:prev page | d<int>:done | b:back ]");

        return scanner.nextLine();
    }
    public void displayMsg(String msg,boolean success){
        Scanner scanner=new Scanner(System.in);
        if(success)
            myLogger.log(greenBgText(msg));
        else
            myLogger.log(errorText(msg));

        myLogger.log("[press enter...]");
        scanner.nextLine();
    }
    public void displayAfterPurchase(String productName,String phoneNumber){
        Scanner scanner=new Scanner(System.in);
        myLogger.log("=====================================================");
        myLogger.log("Thank You for purchasing \""+ blueText(productName)+"\".");
        myLogger.log("We’ll call you on \""+ blueText(phoneNumber)+"\" within 20 minutes for confirmation.");
        myLogger.log("We value your choice.");
        myLogger.log("=====================================================");
        myLogger.log("[press enter...]");
        scanner.nextLine();
    }
    public String displayStart(){
        Scanner scanner=new Scanner(System.in);

        myLogger.log(blueBgText(" Program "));
        myLogger.log("1. "+ blueText("Login"));
        myLogger.log("2. "+ blueText("Sign Up"));
        myLogger.log("3. "+ blueText("Exit"));

        return scanner.nextLine();
    }

}
