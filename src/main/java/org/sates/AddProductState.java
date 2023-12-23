package org.sates;

import org.car.App;
import org.car.MyException;

import java.util.Map;

public class AddProductState implements State {
    private final App myApp;
    private boolean dataIsEmpty;

    public AddProductState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        Map<String,String> data = myApp.getCli().displayAddProduct(myApp.getDatabase().getCategoryList());
        dataIsEmpty=data.values().stream().allMatch(String::isEmpty);
        handleInput(data);
        if(!myApp.getError().getLocation().equals(getStateString())){
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
                throw new MyException();
            if(dataIsEmpty) return;
            int categoryNumber= Integer.parseInt(data.get("category"));
            myApp.addProduct(data.get("name"),myApp.getDatabase().getCategoryList().get(categoryNumber-1).getName(),data.get("description"),Double.parseDouble(data.get("price")));
        }catch (Exception e){
            myApp.getError().setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddProduct";
    }
}
