package org.sates;

import org.car.App;
import org.car.Error;

public class InstallerDashboardState implements State{
    private final App myApp;
    public InstallerDashboardState(App myApp){
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString(),myApp);
        String option = myApp.getCli().displayInstallerDashboard(myApp.searchAccount(myApp.email));
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;
        switch (option) {
            case "1" -> myApp.setState(new ScheduleOfAppointmentsState(myApp));
            case "2" -> myApp.setState(new InstallationRequestsState(myApp));
            case "3" -> {myApp.searchAccount(myApp.email).resetNotificationCount();myApp.setState(new NotificationCenterState(myApp));}
            case "4" -> myApp.setState(new StartState(myApp));
            default -> Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "InstallerDashboard";
    }
}

