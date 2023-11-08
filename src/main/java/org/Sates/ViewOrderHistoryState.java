package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

public class ViewOrderHistoryState implements State {
    private final App myApp;
    public ViewOrderHistoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        String option=Cli.displayOrderHistory(myApp.searchAccount(myApp.email).getOrders());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        if (option.equals("n") && Cli.page != Cli.totalPages) Cli.page++;
        else if (option.equals("p") && Cli.page != 1) Cli.page--;
        else if (option.equals("b")) myApp.setState(new ProfileState(myApp));
        else Error.setError(myApp.getCurrentState().getStateString());
    }

    @Override
    public String getStateString() {
        return "ViewOrderHistory";
    }
}
