package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;
import org.Data.Product;

import java.util.ArrayList;

public class ProductCrudState implements State {
    private final App myApp;
    public ProductCrudState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.PRODUCTS_CRUD);
        ArrayList<Product> productArrayList;

        String option;
        if(myApp.handleManageProductOutput==1){
            productArrayList=myApp.myDatabase.getAllProducts();
        } else{
            productArrayList=myApp.myDatabase.getCategoryList().get(myApp.handleManageProductOutput-3).getProductsList();
        }
        option = Cli.displayProducts(productArrayList);

        //Product CRUD output
        myApp.handleProductCRUD(option,productArrayList);
    }
}
