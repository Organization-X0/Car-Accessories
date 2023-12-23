package org.sates;

import org.car.App;

public class AddCategoryState implements State {
    private final App myApp;
    public AddCategoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String name= myApp.getCli().displayAddCategory();
        handleInput(name);
        if(!myApp.getError().getLocation().equals(getStateString()) && !name.isEmpty()){
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
            myApp.getError().setError(getStateString());
        }
    }

    @Override
    public String getStateString() {
        return "AddCategory";
    }
}
