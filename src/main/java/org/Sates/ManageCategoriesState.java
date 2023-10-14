package org.Sates;

import org.Car.App;
import org.Car.Cli;
import org.Car.Error;
import org.Car.StateEnum;

public class ManageCategoriesState implements State {
    private final App myApp;
    public ManageCategoriesState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(getStateString());
        String option= Cli.displayManageCategories(myApp.myDatabase.getCategoryList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;

        if(option.equals("b")) myApp.setState(new AdminDashboardState(myApp));
        else if(option.equals("a")) myApp.setState(new AddCategoryState(myApp));
        else if(option.charAt(0) == 'd') {
            try{
                int num=Integer.parseInt(option.substring(1));
                String categoryName=myApp.myDatabase.getCategoryList().get(num-1).getName();
                myApp.deleteCategory(categoryName);
                Error.setError(null);
            }catch (Exception e){
                Error.setError(getStateString());
            }
        } else if(option.charAt(0) == 'u') {
            try{
                int num=Integer.parseInt(option.substring(1));
                myApp.categoryNameToUpdate=myApp.myDatabase.getCategoryList().get(num-1).getName();
                myApp.setState(new UpdateCategoryState(myApp));
                Error.setError(null);
            }catch (Exception e){
                Error.setError(getStateString());
            }
        }
    }
    @Override
    public String getStateString() {
        return "ManageCategories";
    }
}
