package org.Car;

import org.Data.User;

import java.util.Map;

public class UpdateAccountState implements State {
    private final App myApp;
    public UpdateAccountState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.UPDATE_ACCOUNT);
        Map<String,String> data=Cli.displayUpdateAccount();

        //Update account
        try{
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
            myApp.updateAccount(myApp.userEmailToUpdate,user);
            myApp.setState(new ManageAccountsState(myApp));
            Error.setError(StateEnum.NO_ERROR);
        }catch (Exception e){
            Error.setError(StateEnum.UPDATE_ACCOUNT);
        }

    }
}
