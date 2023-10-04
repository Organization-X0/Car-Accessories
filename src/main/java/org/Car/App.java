package org.Car;

import org.Data.DataBase;
import org.Data.Product;

import java.util.Map;

public class App {
    public boolean loggedIn;
    public State state;
    private final SignUp mySignUp;
    private final Login myLogin;
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
//        myDatabase.addProduct(new Product("1",name,category,description,price,true));
    }
    public boolean isProductAdded(String name) {
//        return myDatabase.filterProducts(name) != null;
        return true;
    }

    public void updateProduct(String id,Product updatedProduct) {
//        Product product=myDatabase.searchOneProduct(id);
//        if (product != null) {
//            product.setName(updatedProduct.getName() != null ? updatedProduct.getName() : product.getName());
//            product.setPrice(updatedProduct.getPrice() != null ? updatedProduct.getPrice() : product.getPrice());
//            product.setCategory(updatedProduct.getCategory() != null ? updatedProduct.getCategory() : product.getCategory());
//            product.setAvailability(updatedProduct.isAvailability() != product.isAvailability() ? updatedProduct.isAvailability():product.isAvailability());
//            product.setDescription(updatedProduct.getDescription() !=null ? updatedProduct.getDescription() : product.getDescription());
//        }

    }

    public boolean isProductUpdated() {
        return true;
    }

    public void deleteProduct(String id) {
    }

    public boolean isProductDeleted() {
        return true;
    }

    public void browseProducts() {
    }

    public boolean isBrowsed() {
        return true;
    }

    public void searchProduct() {
    }

    public boolean isSearched() {
        return true;
    }
}
