package org.sates;

import org.car.App;
import org.car.Error;

public class ViewInstallationHistoryState implements State {
    private final App myApp;
    public ViewInstallationHistoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayInstallationHistory(myApp.searchAccount(myApp.getEmail()).getInstallations());
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
