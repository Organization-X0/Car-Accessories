package org.Car;

public class AddCategoryState implements State {
    private final App myApp;
    public AddCategoryState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.ADD_CATEGORY);
        String name=Cli.displayUpdateCategory();

        //Add category
        try{
            if(!name.isEmpty())
                myApp.addCategory(name);
            else
                throw new Exception();
            Error.setError(StateEnum.NO_ERROR);
            Cli.displayMsg(" Category added successfully! ",true);
            myApp.setState(new ManageCategoriesState(myApp));
        }catch (Exception e){
            Error.setError(StateEnum.ADD_CATEGORY);
        }

    }
}
