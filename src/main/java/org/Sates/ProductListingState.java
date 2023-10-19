package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Data.Product;

import java.util.ArrayList;

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
        ArrayList<Product> productArrayList;
        if(myApp.handleManageProductOutput==1){
            productArrayList =myApp.myDatabase.getAllProducts();
        } else{
            productArrayList =myApp.myDatabase.getCategoryList().get(myApp.handleManageProductOutput-3).getProductsList();
        }
        option = Cli.displayCustomerProducts(productArrayList);
        handleInput(option);

    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        myApp.handleProductCustomer(option);
    }

    @Override
    public String getStateString() {
        return "ProductListing";
    }
}
