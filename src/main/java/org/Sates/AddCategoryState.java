package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;

public class AddCategoryState implements State {
    private final App myApp;
    public AddCategoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        Error.setError(null);
        String name= Cli.displayAddCategory();
        handleInput(name);
        if(!Error.getLocation().equals(getStateString())){
            Cli.displayMsg(" Category added successfully! ",true);
        }
    }

    @Override
    public void handleInput(Object input) {
        String name = (String)input;
        try{
            if(!name.isEmpty())
                myApp.addCategory(name);
            else
                throw new Exception();
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
