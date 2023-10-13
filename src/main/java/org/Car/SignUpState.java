package org.Car;

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
