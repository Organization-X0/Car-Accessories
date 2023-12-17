package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;

import java.util.Map;

public class AddAppointmentState implements State {
    private final App myApp;
    private boolean dataIsEmpty;
    public AddAppointmentState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Map<String,String> data;
        Error.checkAndShow(getStateString());
        if(myApp.whoLoggedIn().equals("admin")) data = Cli.displayAddAppointment(myApp.myDatabase.getRequestedAppointmentsList(),myApp);
        else data = Cli.displayAddAppointmentCustomer(myApp,myApp.myDatabase.getRequestedAppointmentsList());
        dataIsEmpty=true;
        for (var entry : data.entrySet()) {
            if (!entry.getKey().equals("email") && !entry.getValue().isEmpty()) {
               dataIsEmpty=false;
            }
        }
        handleInput(data);
        if(!Error.getLocation().equals(getStateString())){
            if(!dataIsEmpty) Cli.displayMsg(" Appointment added successfully! ",true);
            myApp.availableTimesShown=false;
            if(myApp.whoLoggedIn().equals("admin")) myApp.setState(new ManageInstallationAppointmentState(myApp));
            else myApp.setState(new CustomerDashboardState(myApp));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try{
            myApp.availableTimesShown=true;
            Map<String,String> data;
            if(input instanceof Map)
                data=(Map<String, String>) input;
            else
                throw new Exception();

            //check data
            if(dataIsEmpty) return;
            if(!App.isValidDate(data.get("date")))
                throw new Exception();
            if(myApp.myDatabase.searchAccount(data.get("email"))==null)
                throw new Exception();
            int timeSlot= Integer.parseInt(data.get("time"));
            if(timeSlot<=0) throw new Exception();

            myApp.addAppointment(data.get("email"),data.get("productName"),data.get("carMake"),data.get("date"),timeSlot);
            User installer=myApp.searchAccount("installer@gmail.com");
            installer.pushNotification("New installation request added.");
            installer.increaseNotificationCount();

        }catch (Exception e){
            Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddAppointment";
    }
}
