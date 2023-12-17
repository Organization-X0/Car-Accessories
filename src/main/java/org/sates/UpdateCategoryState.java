package org.sates;

import org.car.App;
import org.car.Cli;

public class UpdateCategoryState implements State {
    private final App myApp;
    public UpdateCategoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        String newName= Cli.displayUpdateCategory();
        handleInput(newName);
        myApp.setState(new ManageCategoriesState(myApp));
    }

    @Override
    public void handleInput(Object input) {
        String newName = (String) input;

        if(!newName.isEmpty()){
            myApp.searchCategory(myApp.categoryNameToUpdate).getProductsList()
                    .forEach(product -> product.setCategory(newName));
            myApp.updateCategory(myApp.categoryNameToUpdate,newName);
        }
    }
    @Override
    public String getStateString() {
        return "UpdateCategory";
    }
}
