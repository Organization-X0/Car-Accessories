package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

public class ViewInstallationHistoryState implements State {
    private final App myApp;
    public ViewInstallationHistoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option= Cli.displayInstallationHistory(myApp.searchAccount(myApp.email).getInstallations());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        myApp.handleView((String) input);
    }


    @Override
    public String getStateString() {
        return "ViewInstallationHistory";
    }
}
