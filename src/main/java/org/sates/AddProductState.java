package org.sates;

import org.car.App;
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
        Error.checkAndShow(getStateString(),myApp);
        Map<String,String> data = myApp.getCli().displayAddProduct(myApp.myDatabase.getCategoryList());
        dataIsEmpty=data.values().stream().allMatch(String::isEmpty);
        handleInput(data);
        if(!Error.getLocation().equals(getStateString())){
            if(!dataIsEmpty) myApp.getCli().displayMsg(" Product added successfully! ",true);
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
