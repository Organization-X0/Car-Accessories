package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

public class SearchProductState implements State {
    private final App myApp;
    public SearchProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);

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
