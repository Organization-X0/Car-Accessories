package org.sates;

import org.car.App;

public class StartState implements State{
    private final App myApp;
    public StartState(App myApp){
        this.myApp=myApp;
    }
    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option = myApp.getCli().displayStart();
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        switch (option) {
            case "1" -> myApp.setState(new LoginState(myApp));
            case "2" -> myApp.setState(new SignUpState(myApp));
            case "3" -> myApp.setExit(true);
            default -> myApp.getError().setError(getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "Start";
    }
}
