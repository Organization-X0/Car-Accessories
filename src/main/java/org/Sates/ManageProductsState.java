package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

public class ManageProductsState implements State {
    private final App myApp;
    public ManageProductsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option= Cli.displayManageProducts(myApp.myDatabase.getCategoryList());
        handleInput(option);
        Cli.page=1;
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;

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
            Error.setError(null);
            myApp.handleManageProductOutput= intOption;
        }catch (Exception e){
            Error.setError(getStateString());
            myApp.handleManageProductOutput= 0;
        }
    }
    @Override
    public String getStateString() {
        return "ManageProducts";
    }
}
