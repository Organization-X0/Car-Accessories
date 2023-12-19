package org.sates;

import org.car.App;
import org.car.Error;

public class ManageAccountsState implements State {
    private final App myApp;
    public ManageAccountsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayManageAccounts(myApp.getDatabase().getCustomerList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option =(String) input;

        myApp.nextPrevBack(option,new AdminDashboardState(myApp));
        if(!option.isEmpty() && option.charAt(0) == 'd') {
            try{
                int num=Integer.parseInt(option.substring(1));
                String userEmail=myApp.getDatabase().getCustomerList().get(num-1).getEmail();
                myApp.deleteAccount(userEmail);
            }catch (Exception e){
                myApp.getError().setError(getStateString());
            }
        } else if(!option.isEmpty() && option.charAt(0) == 'u') {
            try{
                int num=Integer.parseInt(option.substring(1));
                myApp.userEmailToUpdate=myApp.getDatabase().getCustomerList().get(num-1).getEmail();
                myApp.setState(new UpdateAccountState(myApp));
            }catch (Exception e){
                myApp.getError().setError(getStateString());
            }
        }else myApp.getError().setError(getStateString());
    }
    @Override
    public String getStateString() {
        return "ManageAccounts";
    }
}
