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
        Error.setError(null);
        String option;
        if(myApp.handleManageProductOutput==1){
            myApp.productArrayListBetweenState =myApp.myDatabase.getAllProducts();
        } else if(myApp.handleManageProductOutput!=2){
            myApp.productArrayListBetweenState =myApp.myDatabase.getCategoryList().get(myApp.handleManageProductOutput-3).getProductsList();
        }
        option = Cli.displayProducts(myApp.productArrayListBetweenState);
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        if(!flag){
            if(myApp.handleManageProductOutput==1){
                myApp.productArrayListBetweenState =myApp.myDatabase.getAllProducts();
            } else if(myApp.handleManageProductOutput!=2){
                myApp.productArrayListBetweenState =myApp.myDatabase.getCategoryList().get(myApp.handleManageProductOutput-3).getProductsList();
            }
        }
        String option = (String) input;

        if (option.equals("n") && Cli.page != Cli.totalPages) Cli.page++;
        else if (option.equals("p") && Cli.page != 1) Cli.page--;
        else if (option.equals("b")) myApp.setState(new ManageProductsState(myApp));
        else if (option.equals("a")) myApp.setState(new AddProductState(myApp));
        else if (!option.isEmpty() && option.charAt(0) == 'd') {
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
