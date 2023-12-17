package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

public class SearchProductState implements State {
    private final App myApp;
    public SearchProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());

        if(Error.getLocation().equals(getStateString()) && myApp.whoLoggedIn().equals("admin"))
            myApp.setState(new ManageProductsState(myApp));
        else
            myApp.setState(new ProductCatalogState(myApp));

        String productName= Cli.displaySearchProduct();
        handleInput(productName);
    }

    @Override
    public void handleInput(Object input) {
        String productName = (String) input;

        myApp.productArrayListBetweenState =myApp.myDatabase.searchProducts(productName);
        if (myApp.whoLoggedIn().equals("admin")){
            myApp.setState(new ProductCrudState(myApp));
        } else {
            myApp.setState(new ProductListingState(myApp));
        }
    }
    @Override
    public String getStateString() {
        return "SearchProduct";
    }
}
