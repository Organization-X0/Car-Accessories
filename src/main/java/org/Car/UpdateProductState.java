package org.Car;

import org.Data.Product;

import java.util.Map;

public class UpdateProductState implements State {
    private final App myApp;
    public UpdateProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.UPDATE_PRODUCT);
        Map<String,String> data = Cli.displayUpdateProduct();

        //Update product
        try{
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
            Error.setError(StateEnum.NO_ERROR);
        }catch (Exception e){
            Error.setError(StateEnum.UPDATE_PRODUCT);
        }
    }
}
