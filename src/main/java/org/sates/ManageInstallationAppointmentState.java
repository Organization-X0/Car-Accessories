package org.sates;

import org.car.App;
import org.data.Appointment;

import java.util.ArrayList;

public class ManageInstallationAppointmentState implements State {
    private final App myApp;

    public ManageInstallationAppointmentState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayInstallationAppointments(myApp.getDatabase().getRequestedAppointmentsList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        ArrayList<Appointment> appointmentArrayList = myApp.getDatabase().getRequestedAppointmentsList();
        String option=(String) input;

        myApp.nextPrevBackAdd(option,new AdminDashboardState(myApp),new AddAppointmentState(myApp));
        if (!option.isEmpty() && option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int appointmentId = appointmentArrayList.get(num - 1).getId();
                myApp.deleteAppointment(appointmentId);
            } catch (Exception e) {
                myApp.getError().setError(getStateString());
            }
        } else if (!option.isEmpty() && option.charAt(0) == 'u') {
            try {
                int num = Integer.parseInt(option.substring(1));
                myApp.setAppointmentIdToUpdate(appointmentArrayList.get(num - 1).getId());
                myApp.setState(new UpdateAppointmentState(myApp));
            } catch (Exception e) {
                myApp.getError().setError(getStateString());
            }
        }else myApp.getError().setError(getStateString());
    }



    @Override
    public String getStateString() {
        return "ManageInstallationAppointments";
    }
}
