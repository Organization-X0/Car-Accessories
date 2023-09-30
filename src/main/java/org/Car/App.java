package org.Car;

import org.Data.DataBase;

import java.util.Map;
import java.util.Scanner;

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
    public void runApp(){
        Scanner scanner=new Scanner(System.in);
        String option=scanner.nextLine();
        setOption(option);
    }
    public State getState() {
        return state;
    }
    public void displayLoginApp(){
        state=State.LOGIN;
        Map<String, String> loginData = myLogin.displayLogin();
        login(loginData.get("email"), loginData.get("password"));
    }
    public void displaySingUpApp(){
        state=State.SIGNUP;
        Map<String, String> signUpData = mySignUp.displaySignUp();
        signUp(signUpData.get("fullname"),signUpData.get("email"),signUpData.get("phone"),signUpData.get("password"));
    }
    public void login(String email, String password) {
        if(myLogin.loginNow(email,password)){
            System.out.println(Cli.blueBoldText("Welcome"));
            state=State.MAIN;
            return;
        }
        System.out.println(Cli.errorText("Not Registered!"));
        errorDisplayedLogin=true;

    }
    public boolean errorDisplayedLogin() {
        return errorDisplayedLogin;
    }

    public void signUp(String fullName, String email,String phone ,String password) {
       if(mySignUp.signUpNow(fullName,email,phone,password)){
//           displayLoginApp();
           state=State.LOGIN;
           return;
       }
        System.out.println(Cli.errorText("Invalid data"));
        errorDisplayedSignUp=true;
    }

    public boolean errorDisplayedSignUp() {
        return errorDisplayedSignUp;
    }

    public void setOption(String option) {
        if (option.equals("1")){
//            displayLoginApp();
            state=State.LOGIN;
        } else if (option.equals("2")) {
//            displaySingUpApp();
            state=State.SIGNUP;
        }else{
            System.out.println(Cli.errorText("Invalid option!"));
            errorDisplayedStart=true;
        }
    }

    public boolean errorDisplayedStart() {
        return errorDisplayedStart;
    }
}
