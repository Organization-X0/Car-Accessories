package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

public class ProfileState implements State {
    private final App myApp;
    public ProfileState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option= Cli.displayProfile();
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;
        switch (option){
            case "1"->myApp.setState(new UpdateAccountState(myApp));//edit profile
//            case "2"->myApp.setState();//view order history
//            case "3"->myApp.setState();//view requests history
            case "4"->myApp.setState(new CustomerDashboardState(myApp));//Back
            default -> Error.setError(getStateString());
        }

    }

    @Override
    public String getStateString() {
        return "Profile";
    }
}
