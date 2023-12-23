package org.sates;

import org.car.App;
import org.data.Appointment;

import java.util.ArrayList;

public class ScheduleOfAppointmentsState implements State {
    private final App myApp;
    public ScheduleOfAppointmentsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayScheduleOfAppointments(myApp.getDatabase().getApprovedAppointmentArrayList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        ArrayList<Appointment> appointmentArrayList = myApp.getDatabase().getApprovedAppointmentArrayList();
        String option=(String) input;
        myApp.nextPrevBack(option,new InstallerDashboardState(myApp));
        if (!option.isEmpty() && option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int appointmentId = appointmentArrayList.get(num - 1).getId();

                myApp.deleteApprovedAppointment(appointmentId);
            } catch (Exception e) {
                myApp.getError().setError(getStateString());
            }
        }else myApp.getError().setError(getStateString());
    }

    @Override
    public String getStateString() {
        return "ScheduleOfAppointments";
    }
}
