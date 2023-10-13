package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;
import org.Data.Product;

import java.util.ArrayList;

public class SearchProductState implements State {
    private final App myApp;
    public SearchProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.SEARCH_PRODUCT);
        String productName= Cli.displaySearchProduct();
        String option = Cli.displayProducts(myApp.myDatabase.searchProducts(productName));
        ArrayList<Product> productArrayList=myApp.myDatabase.searchProducts(productName);

        //handleProductsCRUD
        myApp.handleProductCRUD(option,productArrayList);
        myApp.setState(new ManageProductsState(myApp));
    }
}
