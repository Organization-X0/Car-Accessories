package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

import java.util.Map;

public class AddAppointmentState implements State {
    private final App myApp;
    public AddAppointmentState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        Map<String,String> data = Cli.displayAddAppointment(myApp.myDatabase.getAppointmentsList());
        handleInput(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try{
            Map<String,String> data;
            if(input instanceof Map)
                data=(Map<String, String>) input;
            else
                throw new Exception();

            //check data
            if(!App.isValidDate(data.get("date")))
                throw new Exception();
            if(myApp.myDatabase.searchAccount(data.get("email"))==null)
                throw new Exception();

            myApp.addAppointment(data.get("email"),data.get("productName"),data.get("carMake"),data.get("date"));
            Cli.displayMsg(" Appointment added successfully! ",true);
            myApp.setState(new ManageInstallationAppointmentState(myApp));
        }catch (Exception e){
            Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddAppointment";
    }
}
