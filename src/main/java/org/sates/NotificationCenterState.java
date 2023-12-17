package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

public class NotificationCenterState implements State{
    private final App myApp;
    public NotificationCenterState(App myApp){
        this.myApp=myApp;
    }
    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        String data = Cli.displayNotificationCenter(myApp.searchAccount(myApp.email).getNotifications());
        handleInput(data);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;

        if (option.equals("n") && Cli.page != Cli.totalPages) Cli.page++;
        else if (option.equals("p") && Cli.page != 1) Cli.page--;
        else if (option.equals("b")){
            if(myApp.whoLoggedIn().equals("customer")) myApp.setState(new CustomerDashboardState(myApp));
            else myApp.setState(new InstallerDashboardState(myApp));
        }
        else if (!option.isEmpty() && option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                myApp.searchAccount(myApp.email).deleteNotification(num-1);
            } catch (Exception e) {
                Error.setError(getStateString());
            }
        }else Error.setError(getStateString());
    }

    @Override
    public String getStateString() {
        return "NotificationCenter";
    }
}
