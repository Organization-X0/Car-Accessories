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
        Map<String,String> data;
        Error.checkAndShow(getStateString());
        Error.setError(null);
        if(myApp.whoLoggedIn().equals("admin")) data = Cli.displayAddAppointment(myApp.myDatabase.getRequestedAppointmentsList(),myApp);
        else data = Cli.displayAddAppointmentCustomer(myApp,myApp.myDatabase.getRequestedAppointmentsList());
        handleInput(data);
        if(!Error.getLocation().equals(getStateString())){
            Cli.displayMsg(" Appointment added successfully! ",true);
            if(myApp.whoLoggedIn().equals("admin")) myApp.setState(new ManageInstallationAppointmentState(myApp));
            else myApp.setState(new CustomerDashboardState(myApp));
        }
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

            System.out.println("test0");
            //check data
            if(!App.isValidDate(data.get("date")))
                throw new Exception();
            System.out.println("test0.5");
            if(myApp.myDatabase.searchAccount(data.get("email"))==null)
                throw new Exception();
            System.out.println("test0.75");
            int timeSlot= Integer.parseInt(data.get("time"));
            System.out.println("test1");
            if(timeSlot<=0) throw new Exception();

            System.out.println("test2");
            myApp.addAppointment(data.get("email"),data.get("productName"),data.get("carMake"),data.get("date"),timeSlot);
            System.out.println("test3");
        }catch (Exception e){
            System.out.println(e);
            Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddAppointment";
    }
}
