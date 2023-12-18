package org.sates;

import org.car.App;
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
        Error.checkAndShow(getStateString(),myApp);
        String option;
        myApp.setProductArrayListBetweenState();
        option = myApp.getCli().displayCustomerProducts(myApp.productArrayListBetweenState);
        handleInput(option);

        if(!option.isEmpty() && option.charAt(0) == 'f' && !Error.getLocation().equals(getStateString())){
            String phoneNumber=myApp.searchAccount(myApp.email).getPhone();
            myApp.getCli().displayAfterPurchase(productName,phoneNumber);
        }

    }


    @Override
    public void handleInput(Object input) {
        if(!flag){
            myApp.setProductArrayListBetweenState();
        }
        String option = (String) input;
        if (option.equals("n") && myApp.getCli().getCurrentPage() != myApp.getCli().totalPages) myApp.getCli().nextPage();
        else if (option.equals("p") && myApp.getCli().getCurrentPage() != 1) myApp.getCli().prevPage();
        else if (option.equals("b")) myApp.setState(new ProductCatalogState(myApp));
        else if (!option.isEmpty() && option.charAt(0) == 'f') {
            try {
                int num = Integer.parseInt(option.substring(1));
                productName = myApp.productArrayListBetweenState.get(num - 1).getName();
                User account=myApp.searchAccount(myApp.email);
                account.addOrder(myApp.searchProduct(num));
                account.pushNotification("You bought this product \""+ myApp.getCli().blueText(productName)+"\" successfully.");
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
