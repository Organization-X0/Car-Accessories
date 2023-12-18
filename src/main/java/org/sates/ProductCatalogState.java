package org.sates;

import org.car.App;

public class ProductCatalogState implements State {

    private final App myApp;
    public ProductCatalogState(App myApp) {
        this.myApp = myApp;
    }


    @Override
    public void handle() {
        myApp.handelProductCatalogAndManageProducts(getStateString());
    }
    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        myApp.navigateProductsMenu(option,new ProductListingState(myApp),new SearchProductState(myApp),new CustomerDashboardState(myApp),getStateString());
    }


    @Override
    public String getStateString() {
        return "ProductCatalog";
    }
}
