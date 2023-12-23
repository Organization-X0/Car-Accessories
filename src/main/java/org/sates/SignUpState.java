package org.sates;

import org.car.App;
import org.car.MyException;

import java.util.Map;

public class SignUpState implements State {
    private final App myApp;
    public SignUpState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        Map<String, String> signUpData = myApp.getCli().displaySignUp();
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
                throw new MyException();

            myApp.signUp(signUpData.get("fullName"), signUpData.get("email"), signUpData.get("phone"), signUpData.get("password"));
        }catch (Exception e){
            myApp.error.setError(getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "SignUp";
    }
}
