package org.Car;

public class ManageAccountsState implements State {
    private final App myApp;
    public ManageAccountsState(App myApp) {
        this.myApp=myApp;
    }

    @Override
    public void handle() {
        Error.checkAndShow(StateEnum.MANAGE_ACCOUNTS);
        String option=Cli.displayManageAccounts(myApp.myDatabase.getCustomerList());

        //Manage Accounts output
        if(option.equals("n")&& Cli.page!=Cli.totalPages) Cli.page++;
        else if(option.equals("p") && Cli.page!=1) Cli.page--;
        else if(option.equals("b")) myApp.setState(new AdminDashboardState(myApp));
        else if(option.charAt(0) == 'd') {
            try{
                int num=Integer.parseInt(option.substring(1));
                String userEmail=myApp.myDatabase.getCustomerList().get(num-1).getEmail();
                myApp.deleteAccount(userEmail);
                Error.setError(StateEnum.NO_ERROR);
            }catch (Exception e){
                Error.setError(StateEnum.MANAGE_ACCOUNTS);
            }
        } else if(option.charAt(0) == 'u') {
            try{
                int num=Integer.parseInt(option.substring(1));
                myApp.userEmailToUpdate=myApp.myDatabase.getCustomerList().get(num-1).getEmail();
                myApp.setState(new UpdateAccountState(myApp));
                Error.setError(StateEnum.NO_ERROR);
            }catch (Exception e){
                Error.setError(StateEnum.MANAGE_ACCOUNTS);
            }
        }

    }
}
