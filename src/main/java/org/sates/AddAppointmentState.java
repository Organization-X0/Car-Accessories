package org.sates;

import org.car.App;
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
        myApp.getError().checkAndShow(getStateString(),myApp);
        if(myApp.whoLoggedIn().equals("admin")) data = myApp.getCli().displayAddAppointment(myApp.getDatabase().getRequestedAppointmentsList(),myApp);
        else data = myApp.getCli().displayAddAppointmentCustomer(myApp,myApp.getDatabase().getRequestedAppointmentsList());
        dataIsEmpty=setDataIsEmpty(data);
        handleInput(data);
        if(!myApp.getError().getLocation().equals(getStateString())){
            if(!dataIsEmpty) myApp.getCli().displayMsg(" Appointment added successfully! ",true);
            myApp.availableTimesShown=false;
            if(myApp.whoLoggedIn().equals("admin")) myApp.setState(new ManageInstallationAppointmentState(myApp));
            else myApp.setState(new CustomerDashboardState(myApp));
        }
    }

    private boolean setDataIsEmpty(Map<String, String> data) {
        boolean dataIsEmptyL=true;
        for (var entry : data.entrySet()) {
            if (!entry.getKey().equals("email") && !entry.getValue().isEmpty()) {
               dataIsEmptyL=false;
            }
        }
        return dataIsEmptyL;
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

            dataIsEmpty=setDataIsEmpty(data);
            //check data
            if(dataIsEmpty) return;
            if(!App.isValidDate(data.get("date")))
                throw new Exception();
            if(myApp.getDatabase().searchAccount(data.get("email"))==null)
                throw new Exception();
            int timeSlot= Integer.parseInt(data.get("time"));
            if(timeSlot<=0) throw new Exception();

            myApp.addAppointment(data.get("email"),data.get("productName"),data.get("carMake"),data.get("date"),timeSlot);
            User installer=myApp.searchAccount("installer@gmail.com");
            installer.pushNotification("New installation request added.");
            installer.increaseNotificationCount();

        }catch (Exception e){
            myApp.getError().setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddAppointment";
    }
}
