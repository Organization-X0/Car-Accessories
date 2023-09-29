package org.Car;

public class App {
    public boolean loggedIn;
    public State state;
    private final SignUp mySignUp;

    public App(){
        loggedIn=false;
        state = State.LOGIN;
        mySignUp=new SignUp();
    }
    public State getState() {
        return state;
    }
    public void login(String email, String password) {

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
