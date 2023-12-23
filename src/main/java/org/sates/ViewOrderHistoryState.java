package org.sates;

import org.car.App;

public class ViewOrderHistoryState implements State {
    private final App myApp;
    public ViewOrderHistoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayOrderHistory(myApp.searchAccount(myApp.getEmail()).getOrders());
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
