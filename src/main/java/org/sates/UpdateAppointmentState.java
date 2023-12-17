package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Appointment;
import org.data.Product;

import java.sql.Array;
import java.util.Map;

public class UpdateAppointmentState implements State {
    private final App myApp;
    public UpdateAppointmentState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Map<String,String> data = Cli.displayUpdateAppointment();
        handleInput(data);
    }
    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        myApp.updateProductOrAppointmentOrAccount(input,getStateString());
    }
    @Override
    public String getStateString() {
        return "UpdateAppointment";
    }
}
