package org.sates;

import org.car.App;
import org.car.Cli;
import org.data.User;

public class ProductListingState implements State {
    private final App myApp;
    private boolean flag=false;
    private String productName;
    private boolean productAvailable;

    public ProductListingState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        flag=true;
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option;
        myApp.setProductArrayListBetweenState();
        option = myApp.getCli().displayCustomerProducts(myApp.getProductArrayListBetweenState());
        handleInput(option);
       if(!option.isEmpty() && option.charAt(0) == 'f' && !myApp.getError().getLocation().equals(getStateString())){
           if(productAvailable){
               String phoneNumber=myApp.searchAccount(myApp.getEmail()).getPhone();
               myApp.getCli().displayAfterPurchase(productName,phoneNumber);
           }else{
               myApp.getCli().displayCanNotPurchase();
           }
       }
    }


    @Override
    public void handleInput(Object input) {
        if(!flag){
            myApp.setProductArrayListBetweenState();
        }
        String option = (String) input;
        if (option.equals("n") && myApp.getCli().getCurrentPage() != myApp.getCli().getTotalPages()) myApp.getCli().nextPage();
        else if (option.equals("p") && myApp.getCli().getCurrentPage() != 1) myApp.getCli().prevPage();
        else if (option.equals("b")) myApp.setState(new ProductCatalogState(myApp));
        else if (!option.isEmpty() && option.charAt(0) == 'f') {
            try {
                int num = Integer.parseInt(option.substring(1));
                if(myApp.getProductArrayListBetweenState().get(num-1).isAvailability()) {
                    productAvailable=true;
                    productName = myApp.getProductArrayListBetweenState().get(num - 1).getName();
                    User account = myApp.searchAccount(myApp.getEmail());
                    account.addOrder(myApp.searchProduct(num));
                    account.pushNotification("You bought this product \"" + Cli.blueText(productName) + "\" successfully.");
                    account.increaseNotificationCount();
                }else{
                    productAvailable=false;
                }
            } catch (Exception e) {
                myApp.getError().setError(myApp.getCurrentState().getStateString());
            }
        }
        else myApp.getError().setError(myApp.getCurrentState().getStateString());
    }
    @Override
    public String getStateString() {
        return "ProductListing";
    }
}
