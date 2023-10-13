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
        Error.checkAndShow(StateEnum.SIGNUP);
        Map<String, String> signUpData = Cli.displaySignUp();

        //SignUp
        myApp.signUp(signUpData.get("fullName"), signUpData.get("email"), signUpData.get("phone"), signUpData.get("password"));
    }
}
