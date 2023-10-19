package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

public class ProductListingState implements State {
    private final App myApp;

    public ProductListingState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        String option;
        if(myApp.handleManageProductOutput==1){
            myApp.productArrayListBetweenState =myApp.myDatabase.getAllProducts();
        } else if(myApp.handleManageProductOutput!=2){
            myApp.productArrayListBetweenState =myApp.myDatabase.getCategoryList().get(myApp.handleManageProductOutput-3).getProductsList();
        }
        option = Cli.displayCustomerProducts(myApp.productArrayListBetweenState);
        handleInput(option);

    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        if (option.equals("n") && Cli.page != Cli.totalPages) Cli.page++;
        else if (option.equals("p") && Cli.page != 1) Cli.page--;
        else if (option.equals("b")) myApp.setState(new ProductCatalogState(myApp));
        else Error.setError(myApp.getCurrentState().getStateString());
    }
    @Override
    public String getStateString() {
        return "ProductListing";
    }
}
