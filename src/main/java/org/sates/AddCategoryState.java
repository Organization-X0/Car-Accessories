package org.sates;

import org.car.App;
import org.car.Error;

public class AddCategoryState implements State {
    private final App myApp;
    public AddCategoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString(),myApp);
        String name= myApp.getCli().displayAddCategory();
        handleInput(name);
        if(!Error.getLocation().equals(getStateString()) && !name.isEmpty()){
            myApp.getCli().displayMsg(" Category added successfully! ",true);
        }
    }

    
    @Override
    public void handleInput(Object input) {
        String name = (String)input;
        try{
            if(!name.isEmpty())
                myApp.addCategory(name);
            myApp.setState(new ManageCategoriesState(myApp));
        }catch (Exception e){
            Error.setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddCategory";
    }
}
