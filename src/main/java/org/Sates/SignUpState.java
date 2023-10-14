package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;

import java.util.Map;

public class SignUpState implements State {
    private final App myApp;
    public SignUpState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Map<String, String> signUpData = Cli.displaySignUp();
        handleInput(signUpData);

    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try {
            Map<String, String> signUpData;
            if (input instanceof Map)
                signUpData = (Map<String, String>) input;
            else
                throw new Exception();

            myApp.signUp(signUpData.get("fullName"), signUpData.get("email"), signUpData.get("phone"), signUpData.get("password"));
        }catch (Exception e){
            //Error
        }
    }
    @Override
    public String getStateString() {
        return "SignUp";
    }
}
