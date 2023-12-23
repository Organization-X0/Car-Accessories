package org.sates;

import org.car.App;

public class InstallerDashboardState implements State{
    private final App myApp;
    public InstallerDashboardState(App myApp){
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option = myApp.getCli().displayInstallerDashboard(myApp.searchAccount(myApp.getEmail()));
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;
        switch (option) {
            case "1" -> myApp.setState(new ScheduleOfAppointmentsState(myApp));
            case "2" -> myApp.setState(new InstallationRequestsState(myApp));
            case "3" -> {myApp.searchAccount(myApp.getEmail()).resetNotificationCount();myApp.setState(new NotificationCenterState(myApp));}
            case "4" -> myApp.setState(new StartState(myApp));
            default -> myApp.getError().setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "InstallerDashboard";
    }
}

