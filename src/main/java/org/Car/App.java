package org.Car;

import org.Data.*;
import org.Sates.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    private State state;

    public final SignUp mySignUp;
    public final Login myLogin;
    public int handleManageProductOutput=1;
    public int productIdToUpdate;
    public int appointmentIdToUpdate;
    public boolean exit;
    public final DataBase myDatabase;
    public String categoryNameToUpdate;
    public String userEmailToUpdate;
    public ArrayList<Product> productArrayListBetweenState;

    public String email;


    public App(){

        exit=false;

        myDatabase=new DataBase();
        mySignUp=new SignUp(myDatabase);
        myLogin=new Login(myDatabase);

        Product.setLastId(myDatabase);
        Appointment.setLastId(myDatabase);

        //Initial State
        state=new StartState(this);
    }
    public void setState(State state) {
        this.state = state;
    }
    public State getCurrentState(){
        return state;
    }
    public void render(){
        while(!exit) {
            state.handle();
        }
    }
    public void login(String email,String password){
        if(myLogin.loginNow(email,password)){
            this.email=email;
            if(email.equals("admin@gmail.com"))
                setState(new AdminDashboardState(this));
            else if(email.equals("installer@gmail.com"))
                setState(new InstallerDashboardState(this));
            else
                setState(new CustomerDashboardState(this));

            //Add more else if for installer state and for customer state
            //code here...
            return;
        }
        Error.setError(getCurrentState().getStateString());

    }
    public void signUp(String fullName, String email,String phone ,String password) {
       if(mySignUp.signUpNow(fullName,email,phone,password)){
           setState(new LoginState(this));
           return;
       }
        Error.setError(getCurrentState().getStateString());
    }
    public String whoLoggedIn(){
        if(email.equals("admin@gmail.com"))
            return "admin";
        else if(email.equals("installer@gmail.com"))
            return "installer";
        else
            return "customer";
    }

    public void addProduct(String name, String category, String description, double price ) {
        Category categoryObj=myDatabase.searchCategory(category);
        categoryObj.addProduct(new Product(name,category,description,price,true));
    }

    public void updateProduct(int id,Product updatedProduct) {

        Product product=myDatabase.searchOneProduct(id);
        if (product != null) {
            product.setName(updatedProduct.getName() != null ? updatedProduct.getName() : product.getName());
            product.setPrice(updatedProduct.getPrice() != null ? updatedProduct.getPrice() : product.getPrice());
            product.setCategory(updatedProduct.getCategory() != null ? updatedProduct.getCategory() : product.getCategory());
            product.setAvailability(updatedProduct.isAvailability() != product.isAvailability() ? updatedProduct.isAvailability():product.isAvailability());
            product.setDescription(updatedProduct.getDescription() !=null ? updatedProduct.getDescription() : product.getDescription());
        }
    }
    public void deleteProduct(int id){
        Product product=myDatabase.searchOneProduct(id);
        if(product!=null)
            myDatabase.deleteProduct(product);
    }

    public Product searchProduct(int id) {
        return myDatabase.searchOneProduct(id);
    }

    public void addCategory(String name) {
        myDatabase.addCategory(new Category(name));
    }

    public Category searchCategory(String category) {
        return myDatabase.searchCategory(category);
    }

    public void updateCategory(String name, String newName) {
        Category category=myDatabase.searchCategory(name);
        if(category!=null){
            category.setName(newName);
        }
    }

    public void deleteCategory(String name) {
       Category category=myDatabase.searchCategory(name);
       myDatabase.deleteCategory(category);
    }

    public void deleteAccount(String email) {
        User user=myDatabase.searchAccount(email);
        if(user!=null)
            myDatabase.deleteAccount(user);
    }

    public User searchAccount(String email) {
        return myDatabase.searchAccount(email);
    }

    public void updateAccount(String email,User updatedUser) {
        User user=myDatabase.searchAccount(email);
        if (user != null) {
            user.setFullName(updatedUser.getFullName() != null ? updatedUser.getFullName() : user.getFullName());
            user.setPhone(updatedUser.getPhone() != null ? updatedUser.getPhone() : user.getPhone());
        }
    }

    public void addAppointment(String email, String productName, String carMake, String date,int time) {
        myDatabase.addAppointment(new Appointment(email,productName,carMake,date,Time.fromInteger(time-1)));
    }

    public Appointment searchAppointment(int id) {
        return myDatabase.searchAppointment(id);
    }

    public void deleteAppointment(int id) {
        Appointment appointment=myDatabase.searchAppointment(id);
        if(appointment!=null) myDatabase.deleteAppointment(appointment);
    }
    public void updateAppointment(int id,Appointment updatedAppointment){
        Appointment appointment=myDatabase.searchAppointment(id);
        if (appointment!= null) {
            appointment.setEmail(updatedAppointment.getEmail() != null ? updatedAppointment.getEmail() : appointment.getEmail());
            appointment.setCarMake(updatedAppointment.getCarMake() != null ? updatedAppointment.getCarMake(): appointment.getCarMake());
            appointment.setDate(updatedAppointment.getDate()!= null ? updatedAppointment.getDate(): appointment.getDate());
            appointment.setProductName(updatedAppointment.getProductName() != null ? updatedAppointment.getProductName() : appointment.getProductName());
        }
    }
    public void confirmRequest(int id) {
        Appointment appointment=myDatabase.searchAppointment(id);
        if(appointment!=null) myDatabase.addApprovedAppointment(appointment);
    }
    public static boolean isValidDate(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{1,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

}
