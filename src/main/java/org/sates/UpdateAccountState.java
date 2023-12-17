package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;

import java.util.Map;

public class UpdateAccountState implements State {
    private final App myApp;
    public UpdateAccountState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Map<String,String> data= Cli.displayUpdateAccount();
        handleInput(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        myApp.updateProductOrAppointmentOrAccount(input,getStateString());
    }
    @Override
    public String getStateString() {
        return "UpdateAccount";
    }
}
