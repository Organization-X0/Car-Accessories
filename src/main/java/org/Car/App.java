package org.Car;

import org.Data.DataBase;

import java.util.Map;

public class App {
    public boolean loggedIn;
    public State state;
    private final SignUp mySignUp;
    private final Login myLogin;
    private boolean errorDisplayedLogin;
    private boolean errorDisplayedSignUp;
    private boolean errorDisplayedStart;

    public App(){
        errorDisplayedLogin=false;
        errorDisplayedSignUp=false;
        errorDisplayedStart=false;
        final DataBase myDatabase;
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


    public boolean viewInstallationRequests() {
        return false;
    }

    public boolean scheduleAppointments() {
        return false;
    }

    public boolean viewOrders() {
        return false;
    }

    public boolean makePurchases() {
        return false;
    }

    public boolean browseProduct() {
        return false;
    }

    public boolean manageProducts() {
        return false;
    }

    public boolean manageCategories() {
        return false;
    }

    public boolean manageUserAccounts() {
        return false;
    }
}
