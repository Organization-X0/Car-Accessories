package org.Car;

import java.util.Map;

public class LoginState implements State{
    private final App myApp;
    public LoginState(App myApp){
        this.myApp=myApp;
    }
    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.LOGIN);
        Map<String, String> loginData = Cli.displayLogin();

        //Login
        myApp.login(loginData.get("email"),loginData.get("password"));
    }
}
