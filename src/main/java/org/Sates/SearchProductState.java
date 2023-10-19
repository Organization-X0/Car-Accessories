package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Data.Product;

import java.util.ArrayList;

public class SearchProductState implements State {
    private final App myApp;
    private ArrayList<Product> productArrayList;
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

        String option;
        if(myApp.whoLoggedIn().equals("customer"))
            option = Cli.displayCustomerProducts(myApp.myDatabase.searchProducts(productName));
        else
            option = Cli.displayProducts(myApp.myDatabase.searchProducts(productName));

        productArrayList=myApp.myDatabase.searchProducts(productName);
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;

        if (myApp.whoLoggedIn().equals("admin")) myApp.handleProductCRUD(option,productArrayList);
        else myApp.handleProductCustomer(option);
    }
    @Override
    public String getStateString() {
        return "SearchProduct";
    }
}
