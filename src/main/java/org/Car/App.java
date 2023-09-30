package org.Car;

import org.Data.DataBase;

import java.util.Map;

public class App {
    public boolean loggedIn;
    public State state;
    private final SignUp mySignUp;
    private final Login myLogin;
    public String option;
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
                String option = Cli.displayStart();
                setOption(option);
            } else if (state == State.LOGIN) {
                Map<String, String> loginData = Cli.displayLogin();
                login(loginData.get("email"), loginData.get("password"));
            } else if (state == State.SIGNUP) {
                Map<String, String> signUpData = Cli.displaySignUp();
                signUp(signUpData.get("fullName"), signUpData.get("email"), signUpData.get("phone"), signUpData.get("password"));
            } else if (state==State.MAIN) {
                Cli.displayMain();
            }
        }
    }
    public State getState() {
        return state;
    }
    public void login(String email, String password) {
        if(myLogin.loginNow(email,password)){
            state=State.MAIN;
            return;
        }
        errorDisplayedLogin=true;
    }
    public boolean errorDisplayedLogin() {
        return errorDisplayedLogin;
    }

    public void signUp(String fullName, String email,String phone ,String password) {
       if(mySignUp.signUpNow(fullName,email,phone,password)){
           state=State.LOGIN;
           return;
       }
        errorDisplayedSignUp=true;
    }

    public boolean errorDisplayedSignUp() {
        return errorDisplayedSignUp;
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

    public boolean errorDisplayedStart() {
        return errorDisplayedStart;
    }
}
