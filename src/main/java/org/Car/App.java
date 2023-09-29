package org.Car;

public class App {
    public boolean loggedIn;
    public State state;
    Login login;
    public App(){
        loggedIn=false;
        state = State.LOGIN;
        login=new Login();
    }
    public State getState() {
        return state;
    }
    public void login(String email, String password) {

        if(login.loginNow(email,password)){
            return;

        }

    }
    public boolean errorDisplayedLogin() {
        return false;
    }

    public void signUp(String fullName, String email, String password, String phone) {

    }

    public boolean errorDisplayedSignUp() {
        return false;
    }
}
