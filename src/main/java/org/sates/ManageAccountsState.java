package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

public class ManageAccountsState implements State {
    private final App myApp;
    public ManageAccountsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        String option= Cli.displayManageAccounts(myApp.myDatabase.getCustomerList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option =(String) input;

        if(option.equals("n")&& Cli.page!=Cli.totalPages) Cli.page++;
        else if(option.equals("p") && Cli.page!=1) Cli.page--;
        else if(option.equals("b")) myApp.setState(new AdminDashboardState(myApp));
        else if(!option.isEmpty() && option.charAt(0) == 'd') {
            try{
                int num=Integer.parseInt(option.substring(1));
                String userEmail=myApp.myDatabase.getCustomerList().get(num-1).getEmail();
                myApp.deleteAccount(userEmail);
            }catch (Exception e){
                Error.setError(getStateString());
            }
        } else if(!option.isEmpty() && option.charAt(0) == 'u') {
            try{
                int num=Integer.parseInt(option.substring(1));
                myApp.userEmailToUpdate=myApp.myDatabase.getCustomerList().get(num-1).getEmail();
                myApp.setState(new UpdateAccountState(myApp));
            }catch (Exception e){
                Error.setError(getStateString());
            }
        }else Error.setError(getStateString());
    }
    @Override
    public String getStateString() {
        return "ManageAccounts";
    }
}
