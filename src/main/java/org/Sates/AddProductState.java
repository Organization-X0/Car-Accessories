package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

import java.util.Map;

public class AddProductState implements State {
    private final App myApp;

    public AddProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        Map<String,String> data = Cli.displayAddProduct(myApp.myDatabase.getCategoryList());
        handleInput(data);
        if(!Error.getLocation().equals(getStateString())){
            Cli.displayMsg(" Product added successfully! ",true);
            myApp.setState(new ManageProductsState(myApp));
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public void handleInput(Object input) {
        try{
            Map<String,String> data;
            if(input instanceof Map)
                data=(Map<String, String>) input;
            else
                throw new Exception();
            int categoryNumber= Integer.parseInt(data.get("category"));
            myApp.addProduct(data.get("name"),myApp.myDatabase.getCategoryList().get(categoryNumber-1).getName(),data.get("description"),Double.parseDouble(data.get("price")));
        }catch (Exception e){
            Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddProduct";
    }
}
