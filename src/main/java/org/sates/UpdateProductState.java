package org.sates;

import org.car.App;
import org.car.Error;

import java.util.Map;

public class UpdateProductState implements State {
    private final App myApp;
    public UpdateProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        Map<String,String> data = myApp.getCli().displayUpdateProduct();
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
