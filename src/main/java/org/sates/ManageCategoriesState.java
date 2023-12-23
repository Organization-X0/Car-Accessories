package org.sates;

import org.car.App;

public class ManageCategoriesState implements State {
    private final App myApp;
    public ManageCategoriesState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        myApp.getError().checkAndShow(getStateString(),myApp);
        String option= myApp.getCli().displayManageCategories(myApp.getDatabase().getCategoryList());
        handleInput(option);
    }

    @Override
    public void handleInput(Object input) {
        String option = (String) input;

        if(option.equals("b")) myApp.setState(new AdminDashboardState(myApp));
        else if(option.equals("a")) myApp.setState(new AddCategoryState(myApp));
        else if(!option.isEmpty() && option.charAt(0) == 'd') {
            try{
                int num=Integer.parseInt(option.substring(1));
                String categoryName=myApp.getDatabase().getCategoryList().get(num-1).getName();
                myApp.deleteCategory(categoryName);
            }catch (Exception e){
                myApp.getError().setError(getStateString());
            }
        } else if(!option.isEmpty() && option.charAt(0) == 'u') {
            try{
                int num=Integer.parseInt(option.substring(1));
                myApp.setCategoryNameToUpdate(myApp.getDatabase().getCategoryList().get(num-1).getName());
                myApp.setState(new UpdateCategoryState(myApp));
            }catch (Exception e){
                myApp.getError().setError(getStateString());
            }
        }else myApp.getError().setError(getStateString());
    }
    @Override
    public String getStateString() {
        return "ManageCategories";
    }
}
