package org.sates;

import org.car.App;
import org.car.Cli;
import org.car.Error;

import java.util.Map;

public class AddProductState implements State {
    private final App myApp;
    private boolean dataIsEmpty;

    public AddProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        Map<String,String> data = Cli.displayAddProduct(myApp.myDatabase.getCategoryList());
        dataIsEmpty=data.values().stream().allMatch(String::isEmpty);
        handleInput(data);
        if(!Error.getLocation().equals(getStateString())){
            if(!dataIsEmpty) Cli.displayMsg(" Product added successfully! ",true);
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
            if(dataIsEmpty) return;
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
