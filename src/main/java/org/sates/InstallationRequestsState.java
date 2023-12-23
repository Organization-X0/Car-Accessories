package org.sates;

import org.car.App;
import org.data.Appointment;

import java.util.ArrayList;

public class InstallationRequestsState implements State {
    App myApp;
    public InstallationRequestsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayInstallationRequests(myApp.getDatabase().getRequestedAppointmentsList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        ArrayList<Appointment> appointmentArrayList = myApp.getDatabase().getRequestedAppointmentsList();
        String option=(String) input;
        myApp.nextPrevBack(option,new InstallerDashboardState(myApp));
        if (!option.isEmpty() && option.charAt(0) == 'c') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int appointmentId = appointmentArrayList.get(num - 1).getId();
                myApp.confirmRequest(appointmentId);
            } catch (Exception e) {
                myApp.getError().setError(getStateString());
            }
        }else myApp.getError().setError(getStateString());
    }

    @Override
    public String getStateString() {
        return "InstallationRequests";
    }
}