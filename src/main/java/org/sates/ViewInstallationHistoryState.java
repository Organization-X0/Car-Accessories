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
        Error.setError(null);
        String option= Cli.displayInstallationHistory(myApp.searchAccount(myApp.email).getInstallations());
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
        return "ViewInstallationHistory";
    }
}
