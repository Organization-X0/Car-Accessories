package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Data.User;

import java.util.Map;

public class UpdateAccountState implements State {
    private final App myApp;
    public UpdateAccountState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    @ExcludeFromCodeCoverage
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        Map<String,String> data= Cli.displayUpdateAccount();
        handleInput(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try{
            Map<String,String> data;
            if(input instanceof Map)
                data=(Map<String, String>) input;
            else
                throw new Exception();

            User user =new User();
            if (!data.get("fullName").isEmpty()){
                user.setFullName(data.get("fullName"));
            }if (!data.get("phone").isEmpty()){
                user.setPhone(data.get("phone"));
                //check
                if(data.get("phone").length()!=10)
                    throw new Exception();
                Integer.parseInt(data.get("phone"));
            }
            if (myApp.whoLoggedIn().equals("admin")){
                myApp.updateAccount(myApp.userEmailToUpdate,user);
                myApp.setState(new ManageAccountsState(myApp));
            }
            else{
                myApp.updateAccount(myApp.email,user);
                myApp.setState(new ProfileState(myApp));
            }
        }catch (Exception e){
            Error.setError(getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "UpdateAccount";
    }
}
