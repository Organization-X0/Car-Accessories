package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Appointment;

import java.util.Map;

public class UpdateAppointmentState implements State {
    private final App myApp;

    public UpdateAppointmentState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        Map<String,String> data = Cli.displayUpdateAppointment();
        handleInput(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try{
            Map<String, String> data;
            if (input instanceof Map)
                data = (Map<String, String>) input;
            else
                throw new Exception();

            Appointment appointment = new Appointment();
            if (!data.get("email").isEmpty()){
                appointment.setEmail(data.get("email"));
            }if (!data.get("productName").isEmpty())
                appointment.setProductName(data.get("productName"));
            if (!data.get("carMake").isEmpty())
                appointment.setCarMake(data.get("carMake"));
            if (!data.get("date").isEmpty()) {
                if (!App.isValidDate(data.get("date")))
                    throw new Exception();
                appointment.setCarMake(data.get("carMake"));
            }

            myApp.updateAppointment(myApp.appointmentIdToUpdate,appointment);
            myApp.setState(new ManageInstallationAppointmentState(myApp));
        }catch (Exception e){
            Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "UpdateAppointment";
    }
}
