package org.Sates;

import io.cucumber.java.it.Ma;
import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;
import org.Data.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductCrudState implements State {
    private final App myApp;
    private ArrayList<Product> productArrayList;
    public ProductCrudState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option;
        if(myApp.handleManageProductOutput==1){
            productArrayList=myApp.myDatabase.getAllProducts();
        } else{
            productArrayList=myApp.myDatabase.getCategoryList().get(myApp.handleManageProductOutput-3).getProductsList();
        }
        option = Cli.displayProducts(productArrayList);
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;
        myApp.handleProductCRUD(option,productArrayList);
    }
    @Override
    public String getStateString() {
        return "ProductCRUD";
    }
}
