package org.car;

import org.data.*;
import org.sates.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
    public boolean availableTimesShown;
    private State state;

    public final SignUp mySignUp;
    public final Login myLogin;
    public int handleManageProductOutput=1;
    public int productIdToUpdate;
    public int appointmentIdToUpdate;
    public boolean exit;
    public final DataBase myDatabase;
    public final Cli cli;
    public final Error error;
    public String categoryNameToUpdate;
    public String userEmailToUpdate;
    public ArrayList<Product> productArrayListBetweenState;
    public ArrayList<Time> availableTimes;
    private String email;


    public App(){

        exit=false;
        availableTimesShown=false;
        myDatabase=new DataBase();
        mySignUp=new SignUp(myDatabase);
        myLogin=new Login(myDatabase);
        cli=new Cli();
        error=new Error();

        Product.setLastId(myDatabase);
        Appointment.setLastId(myDatabase);

        //Initial State
        state=new StartState(this);
    }
    public void setState(State state) {
        this.state = state;
    }
    public Cli getCli(){
        return this.cli;
    }
    public Error getError(){
        return this.error;
    }
    public String getEmail() {
        return this.email;
    }
    public DataBase getDatabase() {
        return this.myDatabase;
    }
    public ArrayList<Product> getProductArrayListBetweenState() {
        return productArrayListBetweenState;
    }
    public State getCurrentState(){
        return state;
    }
    public void render(){
        while(!exit) {
            state.handle();
        }
    }
    public void login(String email,String password){
        if(myLogin.loginNow(email,password)){
            this.email=email;
            if(email.equals("admin@gmail.com"))
                setState(new AdminDashboardState(this));
            else if(email.equals("installer@gmail.com"))
                setState(new InstallerDashboardState(this));
            else
                setState(new CustomerDashboardState(this));

            return;
        }
        error.setError(getCurrentState().getStateString());

    }
    public void signUp(String fullName, String email,String phone ,String password) {
       if(mySignUp.signUpNow(fullName,email,phone,password)){
           setState(new LoginState(this));
           return;
       }
        error.setError(getCurrentState().getStateString());
    }
    public String whoLoggedIn(){
        if(email.equals("admin@gmail.com"))
            return "admin";
        else if(email.equals("installer@gmail.com"))
            return "installer";
        else
            return "customer";
    }

    public void addProduct(String name, String category, String description, double price ) {
        Category categoryObj=myDatabase.searchCategory(category);
        categoryObj.addProduct(new Product(name,category,description,price,true));
    }

    public void updateProduct(int id,Product updatedProduct) {

        Product product=myDatabase.searchOneProduct(id);
        if (product != null) {
            product.setName(updatedProduct.getName() != null ? updatedProduct.getName() : product.getName());
            product.setPrice(updatedProduct.getPrice() != null ? updatedProduct.getPrice() : product.getPrice());
            product.setCategory(updatedProduct.getCategory() != null ? updatedProduct.getCategory() : product.getCategory());
            product.setAvailability(updatedProduct.isAvailability() != product.isAvailability() ? updatedProduct.isAvailability():product.isAvailability());
            product.setDescription(updatedProduct.getDescription() !=null ? updatedProduct.getDescription() : product.getDescription());
        }
    }
    public void deleteProduct(int id){
        Product product=myDatabase.searchOneProduct(id);
        if(product!=null)
            myDatabase.deleteProduct(product);
    }

    public Product searchProduct(int id) {
        return myDatabase.searchOneProduct(id);
    }

    public void addCategory(String name) {
        myDatabase.addCategory(new Category(name));
    }

    public Category searchCategory(String category) {
        return myDatabase.searchCategory(category);
    }

    public void updateCategory(String name, String newName) {
        Category category=myDatabase.searchCategory(name);
        if(category!=null){
            category.setName(newName);
        }
    }

    public void deleteCategory(String name) {
       Category category=myDatabase.searchCategory(name);
       myDatabase.deleteCategory(category);
    }

    public void deleteAccount(String email) {
        User user=myDatabase.searchAccount(email);
        if(user!=null)
            myDatabase.deleteAccount(user);
    }

    public User searchAccount(String email) {
        return myDatabase.searchAccount(email);
    }

    public void updateAccount(String email,User updatedUser) {
        User user=myDatabase.searchAccount(email);
        if (user != null) {
            user.setFullName(updatedUser.getFullName() != null ? updatedUser.getFullName() : user.getFullName());
            user.setPhone(updatedUser.getPhone() != null ? updatedUser.getPhone() : user.getPhone());
        }
    }

    public void addAppointment(String email, String productName, String carMake, String date,int time) {
        myDatabase.addAppointment(new Appointment(email,productName,carMake,date,availableTimes.get(time-1)));
    }

    public Appointment searchAppointment(int id) {
        return myDatabase.searchAppointment(id);
    }

    public void deleteAppointment(int id) {
        Appointment appointment=myDatabase.searchAppointment(id);
        if(appointment!=null) myDatabase.deleteAppointment(appointment);
    }
    public void deleteApprovedAppointment(int id) {
        Appointment appointment=myDatabase.searchApprovedAppointment(id);
        if(appointment!=null) myDatabase.deleteApprovedAppointment(appointment);
    }
    public Appointment searchApprovedAppointment(int id) {
        return myDatabase.searchApprovedAppointment(id);
    }
    public void updateAppointment(int id,Appointment updatedAppointment){
        Appointment appointment=myDatabase.searchAppointment(id);
        if (appointment!= null) {
            appointment.setEmail(updatedAppointment.getEmail() != null ? updatedAppointment.getEmail() : appointment.getEmail());
            appointment.setCarMake(updatedAppointment.getCarMake() != null ? updatedAppointment.getCarMake(): appointment.getCarMake());
            appointment.setDate(updatedAppointment.getDate()!= null ? updatedAppointment.getDate(): appointment.getDate());
            appointment.setProductName(updatedAppointment.getProductName() != null ? updatedAppointment.getProductName() : appointment.getProductName());
        }
    }
    public void handelProductCatalogAndManageProducts(String type) {
        error.checkAndShow(type,this);
        String option= getCli().displayManageProducts(getDatabase().getCategoryList());
        getCurrentState().handleInput(option);
        getCli().resetPage();
    }
    public void confirmRequest(int id) {
        Appointment appointment=myDatabase.searchAppointment(id);
        if(appointment!=null){
            myDatabase.addApprovedAppointment(appointment);
            myDatabase.deleteAppointment(appointment);
            searchAccount(appointment.getEmail()).addInstallations(appointment);
        }
    }
    public void nextPrevBackAdd(String option, State backState, State addState) {
        if (option.equals("n") && cli.getCurrentPage() != cli.totalPages) cli.nextPage();
        else if (option.equals("p") && cli.getCurrentPage() != 1) cli.prevPage();
        else if (option.equals("b")) setState(backState);
        else if (option.equals("a")) setState(addState);
    }
    public void nextPrevBack(String option, State backState) {
        if (option.equals("n") && cli.getCurrentPage() != cli.totalPages) cli.nextPage();
        else if (option.equals("p") && cli.getCurrentPage() != 1) cli.prevPage();
        else if (option.equals("b")) setState(backState);
    }
    public void setProductArrayListBetweenState() {
        if(handleManageProductOutput==1){
            productArrayListBetweenState =myDatabase.getAllProducts();
        } else if(handleManageProductOutput!=2){
            productArrayListBetweenState =myDatabase.getCategoryList().get(handleManageProductOutput-3).getProductsList();
        }
    }
    public void handleView(String input) {
        if (input.equals("n") && cli.getCurrentPage() != cli.totalPages) cli.nextPage();
        else if (input.equals("p") && cli.getCurrentPage() != 1) cli.prevPage();
        else if (input.equals("b")) setState(new ProfileState(this));
        else error.setError(getCurrentState().getStateString());
    }
    public void navigateProductsMenu(String option, State first, State second, State third, String errorType) {
        try {
            int intOption = Integer.parseInt(option);
            if (intOption == 1 || (intOption != 2 && intOption < (3 + myDatabase.getCategoryList().size()))) {
                setState(first);
            } else if (intOption == 2) {
                setState(second);
            } else if (intOption == 3 + myDatabase.getCategoryList().size()) {
                setState(third);
            } else {
                throw new Exception("invalid input");
            }
            handleManageProductOutput = intOption;
        } catch (Exception e) {
            error.setError(errorType);
            handleManageProductOutput = 0;
        }
    }
    public void updateProductOrAppointmentOrAccount(Object input,String type){
        try{
            Map<String, String> data;
            if (input instanceof Map)
                data = (Map<String, String>) input;
            else
                throw new Exception();
            State stateToUpdate;
            if(type.equals("UpdateAppointment")){
                Appointment appointment= new Appointment();
                stateToUpdate=new ManageInstallationAppointmentState(this);
                if (!data.get("email").isEmpty()){
                    appointment.setEmail(data.get("email"));
                }
                if (!data.get("productName").isEmpty())
                    appointment.setProductName(data.get("productName"));
                if (!data.get("carMake").isEmpty())
                    appointment.setCarMake(data.get("carMake"));
                if (!data.get("date").isEmpty()) {
                    if (!App.isValidDate(data.get("date")))
                        throw new Exception();
                    appointment.setDate(data.get("date"));
                }
                updateAppointment(appointmentIdToUpdate,appointment);
            }else if(type.equals("UpdateProduct")){
                Product product=new Product();
                stateToUpdate=new ManageProductsState(this);
                if (!data.get("price").isEmpty()){
                    double price=Double.parseDouble(data.get("price"));
                    product.setPrice(price);
                }
                if (!data.get("name").isEmpty())
                    product.setName(data.get("name"));
                if (!data.get("description").isEmpty())
                    product.setDescription(data.get("description"));
                updateProduct(productIdToUpdate,product);
            }else{
                User user =new User();
                if (!data.get("fullName").isEmpty()){
                    user.setFullName(data.get("fullName"));
                }
                if (!data.get("phone").isEmpty()){
                    user.setPhone(data.get("phone"));
                    //check
                    if(data.get("phone").length()!=10)
                        throw new Exception();
                    Integer.parseInt(data.get("phone"));
                }
                if (whoLoggedIn().equals("admin")){
                    updateAccount(userEmailToUpdate,user);
                    stateToUpdate=new ManageAccountsState(this);
                }
                else{
                    updateAccount(email,user);
                    stateToUpdate=new ProfileState(this);
                }
            }
            setState(stateToUpdate);
        }catch (Exception e){
            error.setError(type);
        }
    }
    public static boolean isValidDate(String date) {
        String regex = "^\\d{4}-\\d{2}-\\d{1,2}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

}
