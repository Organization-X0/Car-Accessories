package org.Car;

import org.Data.*;

import java.util.Map;

public class App {
    public boolean loggedIn;
    public State state;
    private final SignUp mySignUp;
    private final Login myLogin;
    public boolean isAccountShown;
    private boolean errorDisplayedLogin;
    private boolean errorDisplayedSignUp;
    private boolean errorDisplayedStart;
    final DataBase myDatabase;

    public App(){
        errorDisplayedLogin=false;
        errorDisplayedSignUp=false;
        errorDisplayedStart=false;
        loggedIn=false;
        state = State.START;

        myDatabase=new DataBase();
        mySignUp=new SignUp(myDatabase);
        myLogin=new Login(myDatabase);
    }
    public void render(){
        while(true) {
            if (state == State.START) {
                if(getErrorStart()){
                    System.out.println(Cli.errorText(" Invalid option! "));
                }
                String option = Cli.displayStart();
                setOption(option);
            } else if (state == State.LOGIN) {
                if(getErrorLogin()){
                    System.out.println(Cli.errorText(" Not registered! "));
                }
                Map<String, String> loginData = Cli.displayLogin();
                login(loginData.get("email"), loginData.get("password"));
            } else if (state == State.SIGNUP) {
                if(getErrorSignUp()){
                    System.out.println(Cli.errorText(" Invalid data! "));
                }
                Map<String, String> signUpData = Cli.displaySignUp();
                signUp(signUpData.get("fullname"), signUpData.get("email"), signUpData.get("phone"), signUpData.get("password"));
            } else if (state==State.CUSTOMER_DASHBOARD) {
                Cli.displayMain();
            }
        }
    }
    public State getState() {
        return state;
    }
    public void login(String email, String password) {
        if(myLogin.loginNow(email,password)){
            state=State.CUSTOMER_DASHBOARD;
            errorDisplayedLogin=false;
            return;
        }
        errorDisplayedLogin=true;
    }

    public void signUp(String fullName, String email,String phone ,String password) {
       if(mySignUp.signUpNow(fullName,email,phone,password)){
           state=State.LOGIN;
           errorDisplayedSignUp=false;
           return;

       }
        errorDisplayedSignUp=true;
    }

    public void setOption(String option) {
        if (option.equals("1")){
            state=State.LOGIN;
        } else if (option.equals("2")) {
            state=State.SIGNUP;
        }else{
            errorDisplayedStart=true;
        }
    }

    public boolean getErrorStart() {
        return errorDisplayedStart;
    }
    public boolean getErrorLogin() {
        return errorDisplayedLogin;
    }
    public boolean getErrorSignUp() {
        return errorDisplayedSignUp;
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
    public void deleteProduct(int id) {
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

    public void addAppointment(String email, String productName, String carMake, String date) {
        myDatabase.addAppointment(new Appointment(email,productName,carMake,date));
    }

    public Appointment searchAppointment(int id) {
        return myDatabase.searchAppointment(id);
    }

    public void deleteAppointment(int id) {
        Appointment appointment=myDatabase.searchAppointment(id);
        if(appointment!=null)
            myDatabase.deleteAppointment(appointment);
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
}
