package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;

public class AdminDashboardState implements State {
    private final App myApp;
    public AdminDashboardState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.ADMIN_DASHBOARD);
        String option = Cli.displayAdminDashboard();

        //Admin Dashboard output
        switch (option) {
            case "1" -> myApp.setState(new ManageProductsState(myApp));
            case "2" -> myApp.setState(new ManageCategoriesState(myApp));
            case "3" -> myApp.setState(new ManageAccountsState(myApp));
            case "4" -> myApp.setState(new StartState(myApp));
            default -> Error.setError(StateEnum.ADMIN_DASHBOARD);
        }
    }
}
