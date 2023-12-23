package org.sates;

import org.car.App;

public class UpdateCategoryState implements State {
    private final App myApp;
    public UpdateCategoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        String newName= myApp.getCli().displayUpdateCategory();
        handleInput(newName);
        myApp.setState(new ManageCategoriesState(myApp));
    }

    @Override
    public void handleInput(Object input) {
        String newName = (String) input;

        if(!newName.isEmpty()){
            myApp.searchCategory(myApp.getCategoryNameToUpdate()).getProductsList()
                    .forEach(product -> product.setCategory(newName));
            myApp.updateCategory(myApp.getCategoryNameToUpdate(),newName);
        }
    }
    @Override
    public String getStateString() {
        return "UpdateCategory";
    }
}
