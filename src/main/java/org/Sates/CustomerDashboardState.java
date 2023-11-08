package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

public class CustomerDashboardState implements State {
    private final App myApp;
    public CustomerDashboardState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option= Cli.displayCustomerDashboard();
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;
        switch (option){
            case "1"->myApp.setState(new ProductCatalogState(myApp));
            case "2"->myApp.setState(new AddAppointmentState(myApp));
            case "3"->myApp.setState(new ProfileState(myApp));
            case "4"->myApp.setState(new StartState(myApp));
            default -> Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "CustomerDashboard";
    }
}
