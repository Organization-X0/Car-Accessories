package org.sates;

import org.car.App;
import org.car.Error;
import org.data.Appointment;

import java.util.ArrayList;

public class ScheduleOfAppointmentsState implements State {
    private final App myApp;
    public ScheduleOfAppointmentsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayScheduleOfAppointments(myApp.myDatabase.getApprovedAppointmentArrayList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        ArrayList<Appointment> appointmentArrayList = myApp.myDatabase.getApprovedAppointmentArrayList();
        String option=(String) input;
        myApp.nextPrevBack(option,new InstallerDashboardState(myApp));
        if (!option.isEmpty() && option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int appointmentId = appointmentArrayList.get(num - 1).getId();

                myApp.deleteApprovedAppointment(appointmentId);
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
