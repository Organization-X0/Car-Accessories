package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

public class ManageProductsState implements State {
    private final App myApp;
    public ManageProductsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.handelProductCatalogAndManageProducts(getStateString());
    }
    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        myApp.navigateProductsMenu(option,new ProductCrudState(myApp),new SearchProductState(myApp),new AdminDashboardState(myApp),getStateString());
    }
    @Override
    public String getStateString() {
        return "ManageProducts";
    }
}
