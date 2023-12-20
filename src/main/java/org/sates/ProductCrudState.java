package org.sates;

import org.car.App;
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
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option;
        myApp.setProductArrayListBetweenState();
        option = myApp.getCli().displayProducts(myApp.getProductArrayListBetweenState());
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
                int productId = myApp.getProductArrayListBetweenState().get(num - 1).getId();
                myApp.deleteProduct(productId);
            } catch (Exception e) {
                myApp.getError().setError(myApp.getCurrentState().getStateString());
            }
        } else if (!option.isEmpty() && option.charAt(0) == 'u') {
            try {
                int num = Integer.parseInt(option.substring(1));
                myApp.productIdToUpdate = myApp.getProductArrayListBetweenState().get(num - 1).getId();
                myApp.setState(new UpdateProductState(myApp));
            } catch (Exception e) {
                myApp.getError().setError(myApp.getCurrentState().getStateString());
            }
        } else {
            myApp.getError().setError(myApp.getCurrentState().getStateString());
        }
    }
    @Override
    public String getStateString() {
        return "ProductCRUD";
    }
}
