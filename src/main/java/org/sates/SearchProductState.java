package org.sates;

import org.car.App;

public class SearchProductState implements State {
    private final App myApp;
    public SearchProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);

        if(myApp.getError().getLocation().equals(getStateString()) && myApp.whoLoggedIn().equals("admin"))
            myApp.setState(new ManageProductsState(myApp));
        else
            myApp.setState(new ProductCatalogState(myApp));

        String productName= myApp.getCli().displaySearchProduct();
        handleInput(productName);
    }

    @Override
    public void handleInput(Object input) {
        String productName = (String) input;

        myApp.setProductArrayListBetweenState(myApp.getDatabase().searchProducts(productName));
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
