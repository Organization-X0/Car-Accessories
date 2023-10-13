package org.Car;

import java.util.Map;

public class AddProductState implements State {
    private final App myApp;

    public AddProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.ADD_PRODUCT);
        Map<String,String> data = Cli.displayAddProduct(myApp.myDatabase.getCategoryList());

        //Add product
        try{
            int categoryNumber= Integer.parseInt(data.get("category"));
            myApp.addProduct(data.get("name"),myApp.myDatabase.getCategoryList().get(categoryNumber-1).getName(),data.get("description"),Double.parseDouble(data.get("price")));
            Error.setError(StateEnum.NO_ERROR);
            Cli.displayMsg(" Product added successfully! ",true);
            myApp.setState(new ManageProductsState(myApp));
        }catch (Exception e){
            Error.setError(StateEnum.ADD_PRODUCT);
        }

    }
}
