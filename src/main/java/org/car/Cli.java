package org.car;

import org.data.*;
import org.fusesource.jansi.Ansi;

import java.util.*;
import java.util.stream.IntStream;

import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

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
    private int page=1;
    private int totalPages=1;
    public static Ansi errorText(String text){
        return ansi().eraseScreen().fgBright(WHITE).bgBright(RED).a(text).reset();
    }
    public static Ansi blueBgText(String text){
        return ansi().eraseScreen().bg(BLUE).fgBright(WHITE).a(text).reset();
    }
    public Ansi blueBoldText(String text){
        return ansi().eraseScreen().fgBright(BLUE).a(text).reset();
    }
    public static Ansi blueText(String text){
        return ansi().eraseScreen().fg(BLUE).a(text).reset();
    }
    public static Ansi greenBgText(String text){
        return ansi().eraseScreen().bg(GREEN).fgBright(WHITE).a(text).reset();
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
        System.out.println(blueBgText(" Login "));
        System.out.print(blueText("Email:"));
        String email=scanner.nextLine();
        System.out.print(blueText("Password:"));
        String pass=scanner.nextLine();
        data.put(EMAIL,email);
        data.put("password",pass);
        return data;
    }
    public Map<String,String> displaySignUp(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data = new HashMap<>();

        System.out.print(blueBgText(" Sing Up ")+"\n"+ blueText("Full Name: "));
        scanner.nextLine();
        data.put("fullName",scanner.nextLine());
        System.out.print(blueText("Email: "));
        data.put(EMAIL,scanner.nextLine());
        System.out.print(blueText("Phone: "));
        data.put("phone",scanner.nextLine());
        System.out.print(blueText("Password: "));
        data.put("password",scanner.nextLine());
        return data;
    }
    public String displayAdminDashboard(){
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("Admin Dashboard:"));
        System.out.println("1. "+ blueText("Manage Products"));
        System.out.println("2. "+ blueText("Manage Categories"));
        System.out.println("3. "+ blueText("Manage User Accounts"));
        System.out.println("4. "+ blueText("Manage Installation Appointments."));
        System.out.println("5. "+ blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public String displayManageProducts(List<Category> categoryArrayList){
        int manageProductsOptions=1;
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText(" Product Menu "));
        System.out.println((manageProductsOptions++)+". "+ blueText("All Products"));
        System.out.println((manageProductsOptions++)+". "+ blueText("Search for a Product"));
        for(Category category : categoryArrayList){
            System.out.println((manageProductsOptions++)+". "+ blueText(category.getName()+ " " + CATEGORY));
        }
        System.out.println(manageProductsOptions+". "+ blueText("Back to Dashboard"));
        return scanner.nextLine();
    }

    public String displayCustomerProducts(List<Product> productArrayList){
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("All Products:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, productArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " + blueText(
                productArrayList.get(i).getName() +
                        CATEGORY_TO_PRINT +
                        productArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                        productArrayList.get(i).getPrice() + " | Description:"+productArrayList.get(i).getDescription() +
                        " | Available:"+productArrayList.get(i).isAvailability()
        )));

        totalPages=(int)Math.ceil(productArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println(B_BACK);
        } else if(totalPages==1){
            System.out.println("[ f<int>:buy product |b:back ]");
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | f<int>:buy product | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | f<int>:buy product | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | f<int>:buy product | b:back ]");

        return scanner.nextLine();
    }
    public String displayProducts(List<Product> productArrayList){
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("All Products:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, productArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " + blueText(
                productArrayList.get(i).getName() +
                        CATEGORY_TO_PRINT +
                        productArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                        productArrayList.get(i).getPrice()+ " | Description:"+productArrayList.get(i).getDescription() +
                        " | Available:"+productArrayList.get(i).isAvailability()
        )));

        totalPages=(int)Math.ceil(productArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ a:add | b:back ]");
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            System.out.println(FULL_CRUD);
        else if(page==totalPages)
            System.out.println("[ p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }

    public Map<String,String> displayAddProduct(List<Category> categoryArrayList){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(blueBgText(" ADD PRODUCT "));

        System.out.println(blueText("Choose " + CATEGORY + ": "));
        int i=1;
        for(Category category : categoryArrayList){
            System.out.println((i++)+". "+ blueText(category.getName()+ " " + CATEGORY));
        }

        String categoryOption = scanner.nextLine();

        data.put(CATEGORY,categoryOption);
        System.out.println(blueText(PRODUCT_NAME));
        data.put("name",scanner.nextLine());
        System.out.println(blueText("Description: "));
        data.put("description",scanner.nextLine());
        System.out.println(blueText("Price : "));
        data.put("price",scanner.nextLine());
        return data;
    }
    public String displaySearchProduct(){
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("SEARCH"));
        System.out.print(blueText("Product name:"));
        return scanner.nextLine();
    }
    public Map<String,String> displayUpdateProduct(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(blueBgText(" UPDATE PRODUCT "));
        System.out.println(IF_U_WANT_UPDATE);

        System.out.println(blueText(PRODUCT_NAME));
        data.put("name",scanner.nextLine());
        System.out.println(blueText("Description: "));
        data.put("description",scanner.nextLine());
        System.out.println(blueText("Price : "));
        data.put("price",scanner.nextLine());
        return data;
    }
    public String displayManageCategories(List<Category> categoryArrayList){
        Scanner scanner=new Scanner(System.in);
        int i=1;
        System.out.println(blueBgText("CATEGORIES"));
        for(Category category : categoryArrayList){
            System.out.println((i++)+". "+ blueText(category.getName()+ " " + CATEGORY));
        }
        System.out.println(FULL_CRUD);
        return scanner.nextLine();
    }
    public String displayAddCategory() {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText(" ADD CATEGORY "));

        System.out.println(blueText("Category name: "));
        return scanner.nextLine();
    }
    public String displayManageAccounts(List<User> userArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText(" MANAGE ACCOUNTS "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, userArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i+1)+". "+ blueText(userArrayList.get(i).getFullName()+", "+userArrayList.get(i).getEmail()+", "+userArrayList.get(i).getPhone())));

        totalPages=(int)Math.ceil(userArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println(B_BACK);
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            System.out.println("[ d<int>:delete | u<int>:update | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }
    public Map<String, String> displayUpdateAccount() {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(blueBgText(" UPDATE ACCOUNT "));
        System.out.println(IF_U_WANT_UPDATE);
        System.out.println(blueText("Full Name: "));
        data.put("fullName", scanner.nextLine());
        System.out.println(blueText("Phone: "));
        data.put("phone", scanner.nextLine());
        return data;
    }
    public String displayUpdateCategory() {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText(" UPDATE CATEGORY "));
        System.out.println("If you don't want to update name just press enter.");

        System.out.println(blueText("Category name: "));
        return scanner.nextLine();
    }
    public String  displayInstallationAppointments(List<Appointment> appointmentArrayList){
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("Manage Installation Appointments:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, appointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " + blueText(appointmentArrayList.get(i).getEmail()+" | "+
                appointmentArrayList.get(i).getDate()+" | "+
                appointmentArrayList.get(i).getProductName()+" | "+
                appointmentArrayList.get(i).getCarMake()+" | "+
                appointmentArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(appointmentArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ a:add | b:back ]");
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            System.out.println(FULL_CRUD);
        else if(page==totalPages)
            System.out.println("[ p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }

    private ArrayList<Time> getAvailableTimes(App myApp,String date){

        ArrayList<Time> availableTimes=new ArrayList<>();
        ArrayList<Appointment> appointmentsWithThisDate = myApp.myDatabase.searchAppointmentsByDate(date);
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
        System.out.println(blueBgText(" ADD APPOINTMENT "));

        System.out.println(blueText("Email of user: "));
        data.put(EMAIL,scanner.nextLine());
        System.out.println(blueText(PRODUCT_NAME));
        data.put(PRODUCT_NAME_DATA,scanner.nextLine());
        System.out.println(blueText(CAR_MAKE));
        data.put(CAR_MAKE_DATA,scanner.nextLine());
        System.out.println(blueText("Date"));
        System.out.println(blueText(YYYY_MM_DD_D));
        data.put("date",scanner.nextLine());

        int i=0;
        myApp.setAvailableTimes(getAvailableTimes(myApp,data.get("date")));
        for(Time time:myApp.getAvailableTimes()){
            i++;
            System.out.println(blueText(i+". ")+Time.timeToPrint(time));
        }
        data.put("time",scanner.nextLine());
        return data;
    }

    public Map<String, String> displayAddAppointmentCustomer(App myApp) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> data = new HashMap<>();
        System.out.println(blueBgText(" ADD APPOINTMENT "));

        data.put(EMAIL, myApp.getEmail());
        System.out.println(blueText(PRODUCT_NAME));
        data.put(PRODUCT_NAME_DATA, scanner.nextLine());
        System.out.println(blueText(CAR_MAKE));
        data.put(CAR_MAKE_DATA, scanner.nextLine());
        System.out.println(blueText("Date"));
        System.out.println(blueText(YYYY_MM_DD_D));
        data.put("date", scanner.nextLine());

        int i=0;
        myApp.setAvailableTimes(getAvailableTimes(myApp,data.get("date")));
        for(Time time:myApp.getAvailableTimes()){
            i++;
            System.out.println(blueText(i+". ")+Time.timeToPrint(time));
        }
        data.put("time",scanner.nextLine());
        return data;
    }
    public Map<String, String> displayUpdateAppointment() {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(blueBgText(" APPOINTMENT PRODUCT "));
        System.out.println(IF_U_WANT_UPDATE);

        System.out.println(blueText("Email of user: "));
        data.put(EMAIL,scanner.nextLine());
        System.out.println(blueText(PRODUCT_NAME));
        data.put(PRODUCT_NAME_DATA,scanner.nextLine());
        System.out.println(blueText(CAR_MAKE));
        data.put(CAR_MAKE_DATA,scanner.nextLine());
        System.out.println(blueText("Date"));
        System.out.println(blueText(YYYY_MM_DD_D));
        data.put("date",scanner.nextLine());
        return data;
    }

    public String displayCustomerDashboard(User account) {
        System.out.println( account.getNotificationCount());
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("Customer Dashboard:"));
        System.out.println("1. "+ blueText("Product Catalog"));
        System.out.println("2. "+ blueText("Request services"));
        System.out.println("3. "+ blueText("Profile"));
        System.out.println("4. "+ blueText("Notifications:")+ blueBgText(" "+account.getNotificationCount()+" "));
        System.out.println("5. "+ blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public String displayProfile(String name, String email, String phone) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("Profile:"));
        System.out.println(blueText("Name: ")+name);
        System.out.println(blueText("email: ")+email);
        System.out.println(blueText("phone: ")+phone);
        System.out.println("=================================");
        System.out.println("1. "+ blueText("Edit Profile"));
        System.out.println("2. "+ blueText("View Order History"));
        System.out.println("3. "+ blueText("View Installation Requests History"));
        System.out.println("4. "+ blueText("Back to Customer Dashboard"));
        return scanner.nextLine();
    }

    public String displayOrderHistory(List<Product> ordersArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("Order History:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, ordersArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " + blueText(
                ordersArrayList.get(i).getName() +
                        CATEGORY_TO_PRINT +
                        ordersArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                        ordersArrayList.get(i).getPrice()
        )));

        totalPages=(int)Math.ceil(ordersArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0 || totalPages==1){
            System.out.println(B_BACK);
        }else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | b:back ]");

        return scanner.nextLine();
    }
    public String displayInstallationHistory(List<Appointment> installationsArrayList ) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("Installation Requests History:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, installationsArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " + blueText(
                installationsArrayList.get(i).getDate()+" | "+
                        installationsArrayList.get(i).getProductName()+" | "+
                        installationsArrayList.get(i).getCarMake()+" | "+
                        installationsArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(installationsArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0 || totalPages==1){
            System.out.println(B_BACK);
        }else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | b:back ]");

        return scanner.nextLine();
    }

    public String displayInstallerDashboard(User account) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText("Installer Dashboard:"));
        System.out.println("1. "+ blueText("Schedule of Appointments"));
        System.out.println("2. "+ blueText("Installation Requests"));
        System.out.println("3. "+ blueText("Notifications:")+ blueBgText(" "+account.getNotificationCount()+" "));
        System.out.println("4. "+ blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public String displayInstallationRequests(List<Appointment> appointmentArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText(" Installation Requests "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, appointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " + blueText(appointmentArrayList.get(i).getEmail()+" | "+
                appointmentArrayList.get(i).getDate()+" | "+
                appointmentArrayList.get(i).getProductName()+" | "+
                appointmentArrayList.get(i).getCarMake()+" | "+
                appointmentArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(appointmentArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println(B_BACK);
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | c<int>:confirm | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | c<int>:confirm | b:back ]");
        else if(totalPages==1)
            System.out.println("[ c<int>:confirm | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | c<int>:confirm | b:back ]");

        return scanner.nextLine();
    }
     public String displayScheduleOfAppointments(List<Appointment> approvedAppointmentArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText(" Schedule Of Appointments "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, approvedAppointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " + blueText(approvedAppointmentArrayList.get(i).getEmail()+" | "+
                approvedAppointmentArrayList.get(i).getDate()+" | "+
                approvedAppointmentArrayList.get(i).getProductName()+" | "+
                approvedAppointmentArrayList.get(i).getCarMake()+" | "+
                approvedAppointmentArrayList.get(i).getStringTime()
        )));

        totalPages=(int)Math.ceil(approvedAppointmentArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println(B_BACK);
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | d<int>:done | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | d<int>:done | b:back ]");
        else if(totalPages==1)
            System.out.println("[ d<int>:done | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | d<int>:done | b:back ]");

        return scanner.nextLine();
    }
    public String displayNotificationCenter(List<String> notifications) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(blueBgText(" Notification Center "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, notifications.size());

        IntStream.range(start, end).forEach(i -> System.out.println((i + 1) + ". " +notifications.get(i)));

        totalPages=(int)Math.ceil(notifications.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println(B_BACK);
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | d<int>:done | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | d<int>:done | b:back ]");
        else if(totalPages==1)
            System.out.println("[ d<int>:done | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | d<int>:done | b:back ]");

        return scanner.nextLine();
    }
    public void displayMsg(String msg,boolean success){
        Scanner scanner=new Scanner(System.in);
        if(success)
            System.out.println(greenBgText(msg));
        else
            System.out.println(errorText(msg));

        System.out.println("[press enter...]");
        scanner.nextLine();
    }
    public void displayAfterPurchase(String productName,String phoneNumber){
        Scanner scanner=new Scanner(System.in);
        System.out.println("=====================================================");
        System.out.println("Thank You for purchasing \""+ blueText(productName)+"\".");
        System.out.println("We’ll call you on \""+ blueText(phoneNumber)+"\" within 20 minutes for confirmation.");
        System.out.println("We value your choice.");
        System.out.println("=====================================================");
        System.out.println("[press enter...]");
        scanner.nextLine();
    }
    public String displayStart(){
        Scanner scanner=new Scanner(System.in);

        System.out.println(blueBgText(" Program "));
        System.out.println("1. "+ blueText("Login"));
        System.out.println("2. "+ blueText("Sign Up"));
        System.out.println("3. "+ blueText("Exit"));

        return scanner.nextLine();
    }

}
