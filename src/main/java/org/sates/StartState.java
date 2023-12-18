package org.sates;

import org.car.App;
import org.car.Error;

public class StartState implements State{
    private final App myApp;
    public StartState(App myApp){
        this.myApp=myApp;
    }
    @Override
    public void handle() {
        Error.checkAndShow(getStateString(),myApp);
        String option = myApp.getCli().displayStart();
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        switch (option) {
            case "1" -> myApp.setState(new LoginState(myApp));
            case "2" -> myApp.setState(new SignUpState(myApp));
            case "3" -> myApp.exit = true;
            case "a" -> myApp.login("admin@gmail.com","a123");
            case "i" -> myApp.login("installer@gmail.com","i123");
            case "c" -> myApp.login("user1@gmail.com","u123");
            default -> Error.setError(getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "Start";
    }
}
