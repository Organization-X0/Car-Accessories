package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.Product;

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
        }catch (Exception e){
            Error.setError(getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "UpdateProduct";
    }
}
