package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Data.Appointment;

import java.util.ArrayList;

public class ManageInstallationAppointmentState implements State {
    private final App myApp;

    public ManageInstallationAppointmentState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        String option= Cli.displayInstallationAppointments(myApp.myDatabase.getRequestedAppointmentsList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        ArrayList<Appointment> appointmentArrayList = myApp.myDatabase.getRequestedAppointmentsList();
        String option=(String) input;

        if (option.equals("n") && Cli.page != Cli.totalPages) Cli.page++;
        else if (option.equals("p") && Cli.page != 1) Cli.page--;
        else if (option.equals("b")) myApp.setState(new AdminDashboardState(myApp));
        else if (option.equals("a")) myApp.setState(new AddAppointmentState(myApp));
        else if (option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int appointmentId = appointmentArrayList.get(num - 1).getId();
                myApp.deleteAppointment(appointmentId);
            } catch (Exception e) {
                Error.setError(getStateString());
            }
        } else if (option.charAt(0) == 'u') {
            try {
                int num = Integer.parseInt(option.substring(1));
                myApp.appointmentIdToUpdate = appointmentArrayList.get(num - 1).getId();
                myApp.setState(new UpdateAppointmentState(myApp));
            } catch (Exception e) {
                Error.setError(getStateString());
            }
        }else Error.setError(getStateString());
    }

    @Override
    public String getStateString() {
        return "ManageInstallationAppointments";
    }
}
