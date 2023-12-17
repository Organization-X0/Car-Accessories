package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;
import org.data.User;

public class ProductListingState implements State {
    private final App myApp;
    private boolean flag=false;
    private String productName;

    public ProductListingState(App myApp) {
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
        option = Cli.displayCustomerProducts(myApp.productArrayListBetweenState);
        handleInput(option);

        if(!option.isEmpty() && option.charAt(0) == 'f' && !Error.getLocation().equals(getStateString())){
            String phoneNumber=myApp.searchAccount(myApp.email).getPhone();
            Cli.displayAfterPurchase(productName,phoneNumber);
        }

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
        else if (option.equals("b")) myApp.setState(new ProductCatalogState(myApp));
        else if (!option.isEmpty() && option.charAt(0) == 'f') {
            try {
                int num = Integer.parseInt(option.substring(1));
                productName = myApp.productArrayListBetweenState.get(num - 1).getName();
                User account=myApp.searchAccount(myApp.email);
                account.addOrder(myApp.searchProduct(num));
                account.pushNotification("You bought this product \""+Cli.blueText(productName)+"\" successfully.");
                account.increaseNotificationCount();
            } catch (Exception e) {
                Error.setError(myApp.getCurrentState().getStateString());
            }
        }
        else Error.setError(myApp.getCurrentState().getStateString());
    }
    @Override
    public String getStateString() {
        return "ProductListing";
    }
}
