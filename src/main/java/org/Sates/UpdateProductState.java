package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;
import org.Data.Product;

import java.util.Map;

public class UpdateProductState implements State {
    private final App myApp;
    public UpdateProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Map<String,String> data = Cli.displayUpdateProduct();
        handleInput(data);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try{
            Map<String, String> data;
            if (input instanceof Map)
                data = (Map<String, String>) input;
            else
                throw new Exception();

            Product product = new Product();
            if (!data.get("price").isEmpty()){
                double price=Double.parseDouble(data.get("price"));
                product.setPrice(price);
            }if (!data.get("name").isEmpty())
                product.setName(data.get("name"));
            if (!data.get("description").isEmpty())
                product.setDescription(data.get("description"));

            myApp.updateProduct(myApp.productIdToUpdate,product);
            myApp.setState(new ManageProductsState(myApp));
            Error.setError(null);
        }catch (Exception e){
            Error.setError(getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "UpdateProduct";
    }
}
