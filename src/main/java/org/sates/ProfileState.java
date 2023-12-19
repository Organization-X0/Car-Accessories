package org.sates;

import org.car.App;
import org.car.Error;

public class ProfileState implements State {
    private final App myApp;
    public ProfileState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String name=myApp.searchAccount(myApp.getEmail()).getFullName();
        String phone=myApp.searchAccount(myApp.getEmail()).getPhone();
        String option= myApp.getCli().displayProfile(name,myApp.getEmail(),phone);
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option=(String) input;
        switch (option){
            case "1"->myApp.setState(new UpdateAccountState(myApp));//edit profile
            case "2"->myApp.setState(new ViewOrderHistoryState(myApp));//view order history
            case "3"->myApp.setState(new ViewInstallationHistoryState(myApp));
            case "4"->myApp.setState(new CustomerDashboardState(myApp));//Back
            default -> myApp.getError().setError(getStateString());
        }

    }

    @Override
    public String getStateString() {
        return "Profile";
    }
}
