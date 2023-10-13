package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;

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
