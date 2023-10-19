package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

import java.util.Map;

public class LoginState implements State{
    private final App myApp;
    public LoginState(App myApp){
        this.myApp=myApp;
    }
    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        Map<String, String> loginData = Cli.displayLogin();
        handleInput(loginData);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try {
            Map<String, String> loginData;
            if (input instanceof Map)
                loginData = (Map<String, String>) input;
            else
                throw new Exception();

            myApp.login(loginData.get("email"),loginData.get("password"));
        }catch (Exception e){
            //Error
        }
    }
    @Override
    public String getStateString() {
        return "Login";
    }
}
