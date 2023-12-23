package org.sates;

import org.car.App;
import org.data.User;

public class CustomerDashboardState implements State {
    private final App myApp;
    private final User account;
    public CustomerDashboardState(App myApp) {
        this.myApp=myApp;
        this.account=myApp.searchAccount(myApp.getEmail());
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayCustomerDashboard(account);
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;
        switch (option){
            case "1"->myApp.setState(new ProductCatalogState(myApp));
            case "2"->myApp.setState(new AddAppointmentState(myApp));
            case "3"->myApp.setState(new ProfileState(myApp));
            case "4"->{account.resetNotificationCount();myApp.setState(new NotificationCenterState(myApp));}
            case "5"->myApp.setState(new StartState(myApp));
            default -> myApp.getError().setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "CustomerDashboard";
    }
}
