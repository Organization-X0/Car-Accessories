package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;

public class ManageProductsState implements State {
    private final App myApp;
    public ManageProductsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.MANAGE_PRODUCTS);
        String option= Cli.displayManageProducts(myApp.myDatabase.getCategoryList());

        //Manage Products output
        try {
            int intOption=Integer.parseInt(option);
            if(intOption==1 || (intOption!=2 && intOption<(3+myApp.myDatabase.getCategoryList().size()))){
                myApp.setState(new ProductCrudState(myApp));
            } else if(intOption==2){
                myApp.setState(new SearchProductState(myApp));
            } else if(intOption==3+myApp.myDatabase.getCategoryList().size()){
                myApp.setState(new AdminDashboardState(myApp));
            } else{
                throw new Exception("invalid input");
            }
            Error.setError(StateEnum.NO_ERROR);
            myApp.handleManageProductOutput= intOption;
        }catch (Exception e){
            Error.setError(StateEnum.MANAGE_PRODUCTS);
            myApp.handleManageProductOutput= 0;
        }
        Cli.page=1;
    }
}
