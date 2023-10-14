package org.Car;

import org.Data.*;
import org.Sates.*;

import java.util.ArrayList;

public class App {
    private State state;
    public boolean loggedIn;
    public StateEnum stateEnum;
    public final SignUp mySignUp;
    public final Login myLogin;
    public int handleManageProductOutput=1;
    public int productIdToUpdate;
    public boolean exit;
    public final DataBase myDatabase;
    public String categoryNameToUpdate;
    public String userEmailToUpdate;


    public App(){
        loggedIn=false;
        exit=false;

        myDatabase=new DataBase();
        mySignUp=new SignUp(myDatabase);
        myLogin=new Login(myDatabase);

        Product.setLastId(myDatabase);

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
    public StateEnum getStateEnum() {
        return stateEnum;
    }

    public void login(String email,String password){
        if(myLogin.loginNow(email,password)){
            if(email.equals("admin@gmail.com"))
                setState(new AdminDashboardState(this));
            //Add more else if for installer state and for customer state
            //code here...

            Error.setError(null);
            return;
        }
        Error.setError(getCurrentState().getStateString());

    }
    public void signUp(String fullName, String email,String phone ,String password) {
       if(mySignUp.signUpNow(fullName,email,phone,password)){
           setState(new LoginState(this));
           Error.setError(null);
           return;
       }
        Error.setError(getCurrentState().getStateString());
    }
    public void handleProductCRUD(String option, ArrayList<Product> productArrayList) {
        if (option.equals("n") && Cli.page != Cli.totalPages) Cli.page++;
        else if (option.equals("p") && Cli.page != 1) Cli.page--;
        else if (option.equals("b")) setState(new ManageProductsState(this));
        else if (option.equals("a")) setState(new AddProductState(this));
        else if (option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int productId = productArrayList.get(num - 1).getId();
                deleteProduct(productId);
                Error.setError(null);
            } catch (Exception e) {
                Error.setError(getCurrentState().getStateString());
            }
        } else if (option.charAt(0) == 'u') {
            try {
                int num = Integer.parseInt(option.substring(1));
                productIdToUpdate = productArrayList.get(num - 1).getId();
                setState(new UpdateProductState(this));
                Error.setError(null);
            } catch (Exception e) {
                Error.setError(getCurrentState().getStateString());
            }
        }
    }

    public int addProduct(String name, String category, String description, double price ) {
        Category categoryObj=myDatabase.searchCategory(category);
        return categoryObj.addProduct(new Product(name,category,description,price,true));
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
