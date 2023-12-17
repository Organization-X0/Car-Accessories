package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Product;

import java.util.Map;

public class UpdateProductState implements State {
    private final App myApp;
    public UpdateProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Map<String,String> data = Cli.displayUpdateProduct();
        handleInput(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        myApp.updateProductOrAppointmentOrAccount(input,getStateString());
    }
    @Override
    public String getStateString() {
        return "UpdateProduct";
    }
}
