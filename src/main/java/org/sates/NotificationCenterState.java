package org.sates;

import org.car.App;
import org.car.Error;

public class NotificationCenterState implements State{
    private final App myApp;
    public NotificationCenterState(App myApp){
        this.myApp=myApp;
    }
    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String data = myApp.getCli().displayNotificationCenter(myApp.searchAccount(myApp.getEmail()).getNotifications());
        handleInput(data);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;

        if (option.equals("n") && myApp.getCli().getCurrentPage() != myApp.getCli().totalPages) myApp.getCli().nextPage();
        else if (option.equals("p") && myApp.getCli().getCurrentPage() != 1) myApp.getCli().prevPage();
        else if (option.equals("b")){
            if(myApp.whoLoggedIn().equals("customer")) myApp.setState(new CustomerDashboardState(myApp));
            else myApp.setState(new InstallerDashboardState(myApp));
        }
        else if (!option.isEmpty() && option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                myApp.searchAccount(myApp.getEmail()).deleteNotification(num-1);
            } catch (Exception e) {
                myApp.getError().setError(getStateString());
            }
        }else myApp.getError().setError(getStateString());
    }

    @Override
    public String getStateString() {
        return "NotificationCenter";
    }
}
