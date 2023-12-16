package org.Car;

import org.Data.*;
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
    public static int page=1;
    public static int totalPages=1;
    public static Ansi errorText(String text){
        return ansi().eraseScreen().fgBright(WHITE).bgBright(RED).a(text).reset();
    }
    public static Ansi blueBgText(String text){
        return ansi().eraseScreen().bg(BLUE).fgBright(WHITE).a(text).reset();
    }
    public static Ansi blueBoldText(String text){
        return ansi().eraseScreen().fgBright(BLUE).a(text).reset();
    }
    public static Ansi blueText(String text){
        return ansi().eraseScreen().fg(BLUE).a(text).reset();
    }
    public static Ansi greenBgText(String text){
        return ansi().eraseScreen().bg(GREEN).fgBright(WHITE).a(text).reset();
    }
    public static Ansi purpleBoldText(String text){
        return ansi().eraseScreen().fgBright(MAGENTA).a(text).reset();
    }
    public static Map<String,String> displayLogin(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String>data=new HashMap<>();
        System.out.println(Cli.blueBgText(" Login "));
        System.out.print(Cli.blueText("Email:"));
//        scanner.nextLine();
        String Email=scanner.nextLine();
        System.out.print(Cli.blueText("Password:"));
        String pass=scanner.nextLine();
        data.put(EMAIL,Email);
        data.put("password",pass);
        return data;
    }
    public static Map<String,String> displaySignUp(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data = new HashMap<>();

        System.out.print(Cli.blueBgText(" Sing Up ")+"\n"+Cli.blueText("Full Name: "));
        scanner.nextLine();
        data.put("fullName",scanner.nextLine());
        System.out.print(Cli.blueText("Email: "));
        data.put(EMAIL,scanner.nextLine());
        System.out.print(Cli.blueText("Phone: "));
        data.put("phone",scanner.nextLine());
        System.out.print(Cli.blueText("Password: "));
        data.put("password",scanner.nextLine());
        return data;
    }
    public static void displayMain(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome");
        //Still in progress...
        String mainOption=scanner.nextLine();
        System.out.println(mainOption);
    }
    public static String displayAdminDashboard(){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Admin Dashboard:"));
        System.out.println("1. "+Cli.blueText("Manage Products"));
        System.out.println("2. "+Cli.blueText("Manage Categories"));
        System.out.println("3. "+Cli.blueText("Manage User Accounts"));
        System.out.println("4. "+Cli.blueText("Manage Installation Appointments."));
        System.out.println("5. "+Cli.blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public static String displayManageProducts(ArrayList<Category> categoryArrayList){
        int manageProductsOptions=1;
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" Product Menu "));
        System.out.println((manageProductsOptions++)+". "+Cli.blueText("All Products"));
        System.out.println((manageProductsOptions++)+". "+Cli.blueText("Search for a Product"));
        for(Category category : categoryArrayList){
            System.out.println((manageProductsOptions++)+". "+Cli.blueText(category.getName()+ " " + CATEGORY));
        }
        System.out.println(manageProductsOptions+". "+Cli.blueText("Back to Dashboard"));
        return scanner.nextLine();
    }

    public static String displayCustomerProducts(ArrayList<Product> productArrayList){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("All Products:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, productArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " + Cli.blueText(
                    productArrayList.get(i).getName() +
                            CATEGORY_TO_PRINT +
                            productArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                            productArrayList.get(i).getPrice()
            ));
        });

        totalPages=(int)Math.ceil(productArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ b:back ]");
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
    public static String displayProducts(ArrayList<Product> productArrayList){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("All Products:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, productArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " + Cli.blueText(
                    productArrayList.get(i).getName() +
                            CATEGORY_TO_PRINT +
                            productArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                            productArrayList.get(i).getPrice()
            ));
        });

        totalPages=(int)Math.ceil(productArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ a:add | b:back ]");
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            System.out.println("[ a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }

    public static Map<String,String> displayAddProduct(ArrayList<Category> categoryArrayList){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(Cli.blueBgText(" ADD PRODUCT "));

        System.out.println(Cli.blueText("Choose " + CATEGORY + ": "));
        int i=1;
        for(Category category : categoryArrayList){
            System.out.println((i++)+". "+Cli.blueText(category.getName()+ " " + CATEGORY));
        }

        String categoryOption = scanner.nextLine();

        data.put(CATEGORY,categoryOption);
        System.out.println(Cli.blueText("Product name: "));
        data.put("name",scanner.nextLine());
        System.out.println(Cli.blueText("Description: "));
        data.put("description",scanner.nextLine());
        System.out.println(Cli.blueText("Price : "));
        data.put("price",scanner.nextLine());
        return data;
    }
    public static String displaySearchProduct(){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("SEARCH"));
        System.out.print(Cli.blueText("Product name:"));
        return scanner.nextLine();
    }
    public static Map<String,String> displayUpdateProduct(){
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(Cli.blueBgText(" UPDATE PRODUCT "));
        System.out.println("If you don't want to update specific field just press enter.");

        System.out.println(Cli.blueText("Product name: "));
        data.put("name",scanner.nextLine());
        System.out.println(Cli.blueText("Description: "));
        data.put("description",scanner.nextLine());
        System.out.println(Cli.blueText("Price : "));
        data.put("price",scanner.nextLine());
        return data;
    }
    public static String displayManageCategories(ArrayList<Category> categoryArrayList){
        Scanner scanner=new Scanner(System.in);
        int i=1;
        System.out.println(Cli.blueBgText("CATEGORIES"));
        for(Category category : categoryArrayList){
            System.out.println((i++)+". "+Cli.blueText(category.getName()+ " " + CATEGORY));
        }
        System.out.println("[ a:add | d<int>:delete | u<int>:update | b:back ]");
        return scanner.nextLine();
    }
    public static String displayAddCategory() {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" ADD CATEGORY "));

        System.out.println(Cli.blueText("Category name: "));
        return scanner.nextLine();
    }
    public static String displayManageAccounts(ArrayList<User> userArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" MANAGE ACCOUNTS "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, userArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i+1)+". "+Cli.blueText(userArrayList.get(i).getFullName()+", "+userArrayList.get(i).getEmail()+", "+userArrayList.get(i).getPhone()));
        });

        totalPages=(int)Math.ceil(userArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ b:back ]");
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
    public static Map<String, String> displayUpdateAccount() {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(Cli.blueBgText(" UPDATE ACCOUNT "));
        System.out.println("If you don't want to update specific field just press enter.");
        System.out.println(Cli.blueText("Full Name: "));
        data.put("fullName", scanner.nextLine());
        System.out.println(Cli.blueText("Phone: "));
        data.put("phone", scanner.nextLine());
        return data;
    }
    public static String displayUpdateCategory() {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" UPDATE CATEGORY "));
        System.out.println("If you don't want to update name just press enter.");

        System.out.println(Cli.blueText("Category name: "));
        return scanner.nextLine();
    }
    public static String  displayInstallationAppointments(ArrayList<Appointment> appointmentArrayList){
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Manage Installation Appointments:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, appointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " + Cli.blueText(appointmentArrayList.get(i).getEmail()+" | "+
                    appointmentArrayList.get(i).getDate()+" | "+
                    appointmentArrayList.get(i).getProductName()+" | "+
                    appointmentArrayList.get(i).getCarMake()+" | "+
                    appointmentArrayList.get(i).getStringTime()
            ));
        });

        totalPages=(int)Math.ceil(appointmentArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ a:add | b:back ]");
        } else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(totalPages==1)
            System.out.println("[ a:add | d<int>:delete | u<int>:update | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | a:add | d<int>:delete | u<int>:update | b:back ]");

        return scanner.nextLine();
    }

    private static ArrayList<Time> getAvailableTimes(App myApp,String date){

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
    public static Map<String, String> displayAddAppointment(ArrayList<Appointment> appointmentsList, App myApp) {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(Cli.blueBgText(" ADD APPOINTMENT "));

        System.out.println(Cli.blueText("Email of user: "));
        data.put(EMAIL,scanner.nextLine());
        System.out.println(Cli.blueText("Product name: "));
        data.put("productName",scanner.nextLine());
        System.out.println(Cli.blueText("Car Make: "));
        data.put("carMake",scanner.nextLine());
        System.out.println(Cli.blueText("Date"));
        System.out.println(Cli.blueText("YYYY-MM-DD/D: "));
        data.put("date",scanner.nextLine());

        int i=0;
        myApp.availableTimes=getAvailableTimes(myApp,data.get("date"));
        for(Time time:myApp.availableTimes){
            i++;
            System.out.println(Cli.blueText(i+". ")+Time.timeToPrint(time));
        }
        data.put("time",scanner.nextLine());
        return data;
    }

    public static Map<String, String> displayAddAppointmentCustomer(App myApp,ArrayList<Appointment> appointmentsList) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> data = new HashMap<>();
        System.out.println(Cli.blueBgText(" ADD APPOINTMENT "));

        data.put(EMAIL, myApp.email);
        System.out.println(Cli.blueText("Product name: "));
        data.put("productName", scanner.nextLine());
        System.out.println(Cli.blueText("Car Make: "));
        data.put("carMake", scanner.nextLine());
        System.out.println(Cli.blueText("Date"));
        System.out.println(Cli.blueText("YYYY-MM-DD/D: "));
        data.put("date", scanner.nextLine());

        int i=0;
        myApp.availableTimes=getAvailableTimes(myApp,data.get("date"));
        for(Time time:myApp.availableTimes){
            i++;
            System.out.println(Cli.blueText(i+". ")+Time.timeToPrint(time));
        }
        data.put("time",scanner.nextLine());
        return data;
    }
    public static Map<String, String> displayUpdateAppointment() {
        Scanner scanner=new Scanner(System.in);
        Map<String,String> data=new HashMap<>();
        System.out.println(Cli.blueBgText(" APPOINTMENT PRODUCT "));
        System.out.println("If you don't want to update specific field just press enter.");

        System.out.println(Cli.blueText("Email of user: "));
        data.put(EMAIL,scanner.nextLine());
        System.out.println(Cli.blueText("Product name: "));
        data.put("productName",scanner.nextLine());
        System.out.println(Cli.blueText("Car Make: "));
        data.put("carMake",scanner.nextLine());
        System.out.println(Cli.blueText("Date"));
        System.out.println(Cli.blueText("YYYY-MM-DD/D: "));
        data.put("date",scanner.nextLine());
        return data;
    }

    public static String displayCustomerDashboard(User account) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Customer Dashboard:"));
        System.out.println("1. "+Cli.blueText("Product Catalog"));
        System.out.println("2. "+Cli.blueText("Request services"));
        System.out.println("3. "+Cli.blueText("Profile"));
        System.out.println("4. "+Cli.blueText("Notifications:")+Cli.blueBgText(" "+account.getNotificationCount()+" "));
        System.out.println("5. "+Cli.blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public static String displayProfile(String name, String email, String phone) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Profile:"));
        System.out.println(Cli.blueText("Name: ")+name);
        System.out.println(Cli.blueText("email: ")+email);
        System.out.println(Cli.blueText("phone: ")+phone);
        System.out.println("=================================");
        System.out.println("1. "+Cli.blueText("Edit Profile"));
        System.out.println("2. "+Cli.blueText("View Order History"));
        System.out.println("3. "+Cli.blueText("View Installation Requests History"));
        System.out.println("4. "+Cli.blueText("Back to Customer Dashboard"));
        return scanner.nextLine();
    }

    public static String displayOrderHistory(ArrayList<Product> ordersArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Order History:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, ordersArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " + Cli.blueText(
                    ordersArrayList.get(i).getName() +
                            CATEGORY_TO_PRINT +
                            ordersArrayList.get(i).getCategory() + PRICE_TO_PRINT +
                            ordersArrayList.get(i).getPrice()
            ));
        });

        totalPages=(int)Math.ceil(ordersArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0 || totalPages==1){
            System.out.println("[ b:back ]");
        }else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | b:back ]");

        return scanner.nextLine();
    }
    public static String displayInstallationHistory(ArrayList<Appointment> installationsArrayList ) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Installation Requests History:"));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, installationsArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " + Cli.blueText(
                    installationsArrayList.get(i).getDate()+" | "+
                            installationsArrayList.get(i).getProductName()+" | "+
                            installationsArrayList.get(i).getCarMake()+" | "+
                            installationsArrayList.get(i).getStringTime()
            ));
        });

        totalPages=(int)Math.ceil(installationsArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0 || totalPages==1){
            System.out.println("[ b:back ]");
        }else if(page<totalPages && page>1)
            System.out.println("[ n:next page | p:prev page | b:back ]");
        else if (page<totalPages && page==1)
            System.out.println("[ n:next page | b:back ]");
        else if(page==totalPages)
            System.out.println("[ p:prev page | b:back ]");

        return scanner.nextLine();
    }

    public static String displayInstallerDashboard(User account) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText("Installer Dashboard:"));
        System.out.println("1. "+Cli.blueText("Schedule of Appointments"));
        System.out.println("2. "+Cli.blueText("Installation Requests"));
        System.out.println("3. "+Cli.blueText("Notifications:")+Cli.blueBgText(" "+account.getNotificationCount()+" "));
        System.out.println("4. "+Cli.blueText(LOG_OUT));
        return scanner.nextLine();
    }
    public static String displayInstallationRequests(ArrayList<Appointment> appointmentArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" Installation Requests "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, appointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " + Cli.blueText(appointmentArrayList.get(i).getEmail()+" | "+
                    appointmentArrayList.get(i).getDate()+" | "+
                    appointmentArrayList.get(i).getProductName()+" | "+
                    appointmentArrayList.get(i).getCarMake()+" | "+
                    appointmentArrayList.get(i).getStringTime()
            ));
        });

        totalPages=(int)Math.ceil(appointmentArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ b:back ]");
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
    public static String displayScheduleOfAppointments(ArrayList<Appointment> approvedAppointmentArrayList) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" Schedule Of Appointments "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, approvedAppointmentArrayList.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " + Cli.blueText(approvedAppointmentArrayList.get(i).getEmail()+" | "+
                    approvedAppointmentArrayList.get(i).getDate()+" | "+
                    approvedAppointmentArrayList.get(i).getProductName()+" | "+
                    approvedAppointmentArrayList.get(i).getCarMake()+" | "+
                    approvedAppointmentArrayList.get(i).getStringTime()
            ));
        });

        totalPages=(int)Math.ceil(approvedAppointmentArrayList.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ b:back ]");
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
    public static String displayNotificationCenter(ArrayList<String> notifications) {
        Scanner scanner=new Scanner(System.in);
        System.out.println(Cli.blueBgText(" Notification Center "));

        int start = (page - 1) * 10;
        int end = Math.min(start + 10, notifications.size());

        IntStream.range(start, end).forEach(i -> {
            System.out.println((i + 1) + ". " +notifications.get(i));
        });

        totalPages=(int)Math.ceil(notifications.size()/10.0);
        System.out.println(PAGE_TO_PRINT +page+"/"+totalPages);
        if(totalPages==0){
            System.out.println("[ b:back ]");
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
    public static void displayMsg(String msg,boolean success){
        Scanner scanner=new Scanner(System.in);
        if(success)
            System.out.println(Cli.greenBgText(msg));
        else
            System.out.println(Cli.errorText(msg));

        System.out.println("[press enter...]");
        scanner.nextLine();
    }
    public static void displayAfterPurchase(String productName,String phoneNumber){
        Scanner scanner=new Scanner(System.in);
        System.out.println("=====================================================");
        System.out.println("Thank You for purchasing \""+Cli.blueText(productName)+"\".");
        System.out.println("We’ll call you on \""+Cli.blueText(phoneNumber)+"\" within 20 minutes for confirmation.");
        System.out.println("We value your choice.");
        System.out.println("=====================================================");
        System.out.println("[press enter...]");
        scanner.nextLine();
    }
    public static String displayStart(){
        Scanner scanner=new Scanner(System.in);

        System.out.println(Cli.blueBgText(" Program "));
        System.out.println("1. "+Cli.blueText("Login"));
        System.out.println("2. "+Cli.blueText("Sign Up"));
        System.out.println("3. "+Cli.blueText("Exit"));

        return scanner.nextLine();
    }

}
