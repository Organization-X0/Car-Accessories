package org.Car;

import org.Data.DataBase;

public class App {
    public boolean loggedIn;
    public State state;
    private final SignUp mySignUp;
    private final Login myLogin;

    public App(){
        final DataBase myDatabase;
        loggedIn=false;
        state = State.LOGIN;

        myDatabase=new DataBase();
        mySignUp=new SignUp(myDatabase);
        myLogin=new Login(myDatabase);
    }
    public State getState() {
        return state;
    }
    public void login(String email, String password) {
        if(myLogin.loginNow(email,password)){
            return;
        }
    }
    public boolean errorDisplayedLogin() {
        return false;
    }

    public void signUp(String fullName, String email, String password, String phone) {
       mySignUp.signUpNow(fullName,email,password,phone);
    }

    public boolean errorDisplayedSignUp() {
        return false;
    }
}
