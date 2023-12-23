package org.sates;

import org.car.App;
import org.car.MyException;

import java.util.Map;

public class LoginState implements State{
    private final App myApp;
    public LoginState(App myApp){
        this.myApp=myApp;
    }
    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        Map<String, String> loginData = myApp.getCli().displayLogin();
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
                throw new MyException();

            myApp.login(loginData.get("email"),loginData.get("password"));
        }catch (Exception e){
            myApp.error.setError(getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "Login";
    }
}
