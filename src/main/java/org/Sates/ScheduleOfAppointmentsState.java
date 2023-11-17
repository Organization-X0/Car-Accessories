package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Data.Appointment;

import java.util.ArrayList;

public class ScheduleOfAppointmentsState implements State {
    private final App myApp;
    public ScheduleOfAppointmentsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        String option= Cli.displayScheduleOfAppointments(myApp.myDatabase.getApprovedAppointmentArrayList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        ArrayList<Appointment> appointmentArrayList = myApp.myDatabase.getAppointmentsList();
        String option=(String) input;

        if (option.equals("n") && Cli.page != Cli.totalPages) Cli.page++;
        else if (option.equals("p") && Cli.page != 1) Cli.page--;
        else if (option.equals("b")) myApp.setState(new InstallerDashboardState(myApp));
        else if (option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int appointmentId = appointmentArrayList.get(num - 1).getId();

                //Code here...
                //myApp.confirmRequest(appointmentId);
            } catch (Exception e) {
                Error.setError(getStateString());
            }
        }else Error.setError(getStateString());
    }

    @Override
    public String getStateString() {
        return "ScheduleOfAppointments";
    }
}
