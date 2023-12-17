package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

public class ProductCrudState implements State {
    private final App myApp;
    private boolean flag=false;
    public ProductCrudState(App myApp) {
            this.myApp=myApp;
    }

    @Override
    public void handle() {
        flag=true;
        Error.checkAndShow(getStateString());
        String option;
        myApp.setProductArrayListBetweenState();
        option = Cli.displayProducts(myApp.productArrayListBetweenState);
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        if(!flag){
            myApp.setProductArrayListBetweenState();
        }
        String option = (String) input;

        myApp.nextPrevBackAdd(option,new ManageProductsState(myApp),new AddProductState(myApp));
        if (!option.isEmpty() && option.charAt(0) == 'd') {
            try {
                int num = Integer.parseInt(option.substring(1));
                int productId = myApp.productArrayListBetweenState.get(num - 1).getId();
                myApp.deleteProduct(productId);
            } catch (Exception e) {
                Error.setError(myApp.getCurrentState().getStateString());
            }
        } else if (!option.isEmpty() && option.charAt(0) == 'u') {
            try {
                int num = Integer.parseInt(option.substring(1));
                myApp.productIdToUpdate = myApp.productArrayListBetweenState.get(num - 1).getId();
                myApp.setState(new UpdateProductState(myApp));
            } catch (Exception e) {
                Error.setError(myApp.getCurrentState().getStateString());
            }
        } else {
            Error.setError(myApp.getCurrentState().getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "ProductCRUD";
    }
}
