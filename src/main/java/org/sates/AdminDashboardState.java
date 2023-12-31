package org.sates;

import org.car.App;

public class AdminDashboardState implements State {
    private final App myApp;
    public AdminDashboardState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option = myApp.getCli().displayAdminDashboard();
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;
        switch (option) {
            case "1" -> myApp.setState(new ManageProductsState(myApp));
            case "2" -> myApp.setState(new ManageCategoriesState(myApp));
            case "3" -> myApp.setState(new ManageAccountsState(myApp));
            case "4" -> myApp.setState(new ManageInstallationAppointmentState(myApp));
            case "5" -> myApp.setState(new StartState(myApp));
            default -> myApp.getError().setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AdminDashboard";
    }
}
