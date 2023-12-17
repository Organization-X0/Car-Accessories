package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Appointment;

import java.util.ArrayList;

public class ManageInstallationAppointmentState implements State {
    private final App myApp;

    public ManageInstallationAppointmentState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option= Cli.displayInstallationAppointments(myApp.myDatabase.getRequestedAppointmentsList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        ArrayList<Appointment> appointmentArrayList = myApp.myDatabase.getRequestedAppointmentsList();
        String option=(String) input;

        myApp.nextPrevBackAdd(option,new AdminDashboardState(myApp),new AddAppointmentState(myApp));
        if (!option.isEmpty() && option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int appointmentId = appointmentArrayList.get(num - 1).getId();
                myApp.deleteAppointment(appointmentId);
            } catch (Exception e) {
                Error.setError(getStateString());
            }
        } else if (!option.isEmpty() && option.charAt(0) == 'u') {
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
