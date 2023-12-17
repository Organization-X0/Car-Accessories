package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

public class ViewOrderHistoryState implements State {
    private final App myApp;
    public ViewOrderHistoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option=Cli.displayOrderHistory(myApp.searchAccount(myApp.email).getOrders());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        myApp.handleView((String) input);
    }

    @Override
    public String getStateString() {
        return "ViewOrderHistory";
    }
}
