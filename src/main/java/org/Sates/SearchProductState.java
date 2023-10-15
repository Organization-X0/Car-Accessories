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
        String productName= Cli.displaySearchProduct();
        String option = Cli.displayProducts(myApp.myDatabase.searchProducts(productName));
        productArrayList=myApp.myDatabase.searchProducts(productName);
        handleInput(option);
        myApp.setState(new ManageProductsState(myApp));
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        myApp.handleProductCRUD(option,productArrayList);
    }
    @Override
    public String getStateString() {
        return "SearchProduct";
    }
}
