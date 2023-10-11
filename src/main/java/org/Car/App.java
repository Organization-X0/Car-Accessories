package org.Car;

import org.Data.*;

import java.util.ArrayList;
import java.util.Map;

public class App {
    public boolean loggedIn;
    public State state;
    private final SignUp mySignUp;
    private final Login myLogin;
    private int handleManageProductOutput=1;
    private int productIdToUpdate;
    private boolean exit;
    final DataBase myDatabase;

    public App(){
        loggedIn=false;
        exit=false;
        state = State.START;

        myDatabase=new DataBase();
        mySignUp=new SignUp(myDatabase);
        myLogin=new Login(myDatabase);

        Product.setLastId(myDatabase);
    }
    public void render(){
        while(!exit) {
            if (state == State.START) {
                if(Error.getLocation().equals(State.START)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                String option = Cli.displayStart();
                handleStartOption(option);
            } else if (state == State.LOGIN) {
                if(Error.getLocation().equals(State.LOGIN)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                Map<String, String> loginData = Cli.displayLogin();
                login(loginData.get("email"), loginData.get("password"));
            } else if (state == State.SIGNUP) {
                if(Error.getLocation().equals(State.SIGNUP)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                Map<String, String> signUpData = Cli.displaySignUp();
                signUp(signUpData.get("fullName"), signUpData.get("email"), signUpData.get("phone"), signUpData.get("password"));
            } else if (state==State.CUSTOMER_DASHBOARD) {
                Cli.displayMain();
            } else if(state==State.ADMIN_DASHBOARD){
                if(Error.getLocation().equals(State.ADMIN_DASHBOARD)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                String option = Cli.displayAdminDashboard();
                handleAdminDashboard(option);
                System.out.println(option);
            } else if(state==State.MANAGE_PRODUCTS){
                if(Error.getLocation().equals(State.MANAGE_PRODUCTS)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                String option=Cli.displayManageProducts(myDatabase.getCategoryList());
                handleManageProductOutput= handleManageProduct(option);
                Cli.page=1;
            } else if(state==State.PRODUCTS_CRUD){
                if(Error.getLocation().equals(State.PRODUCTS_CRUD)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                if(handleManageProductOutput==1){
                    String option = Cli.displayProducts(myDatabase.getAllProducts());
                    handleProductsCRUD(option,myDatabase.getAllProducts());
                } else{
                    String optionCrud=Cli.displayProducts(myDatabase.getCategoryList().get(handleManageProductOutput-3).getProductsList());
                    handleProductsCRUD(optionCrud,myDatabase.getCategoryList().get(handleManageProductOutput-3).getProductsList());
                }
            } else if(state==State.ADD_PRODUCT){
                if(Error.getLocation().equals(State.ADD_PRODUCT)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                Map<String,String> data = Cli.displayAddProduct(myDatabase.getCategoryList());
                handleAddProduct(data);
            } else if(state==State.SEARCH_PRODUCT){
                if(Error.getLocation().equals(State.SEARCH_PRODUCT)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                String productName=Cli.displaySearchProduct();
                String option = Cli.displayProducts(myDatabase.searchProducts(productName));
                handleProductsCRUD(option,myDatabase.searchProducts(productName));
                state=State.MANAGE_PRODUCTS;
            } else if(state==State.UPDATE_PRODUCT){
                if(Error.getLocation().equals(State.UPDATE_PRODUCT)){
                    Cli.displayMsg(Error.getMsg(),false);
                }
                Map<String,String> data = Cli.displayUpdateProduct(myDatabase.getCategoryList());
                handleUpdateProduct(data);
            }
        }
    }
    public void handleStartOption(String option) {
        switch (option) {
            case "1" -> state = State.LOGIN;
            case "2" -> state = State.SIGNUP;
            case "3" -> exit = true;
            case "a" -> login("admin@gmail.com","a123");
            case "i" -> login("installer@gmail.com","i123");
            case "c" -> login("user1@gmail.com","u123");
            default -> Error.setError(State.START);
        }
}
    public void handleAdminDashboard(String option){
        switch (option) {
            case "1" -> state = State.MANAGE_PRODUCTS;
            case "2" -> state = State.MANAGE_CATEGORIES;
            case "3" -> state = State.MANAGE_ACCOUNTS;
            case "4" -> state = State.START;
            default -> Error.setError(State.ADMIN_DASHBOARD);
        }
    }
    public int handleManageProduct(String option){
        try {
            int intOption=Integer.parseInt(option);
            if(intOption==1 || (intOption!=2 && intOption<(3+myDatabase.getCategoryList().size()))){
                state=State.PRODUCTS_CRUD;
            } else if(intOption==2){
                state=State.SEARCH_PRODUCT;
            } else if(intOption==3+myDatabase.getCategoryList().size()){
                state=State.ADMIN_DASHBOARD;
            } else{
               throw new RuntimeException("invalid input");
            }
            Error.setError(State.NO_ERROR);
            return intOption;
        }catch (Exception e){
           Error.setError(State.MANAGE_PRODUCTS);
           return 0;
        }
    }
    public void handleProductsCRUD(String option, ArrayList<Product> productArrayList){
        if(option.equals("n")&& Cli.page!=Cli.totalPages) Cli.page++;
        else if(option.equals("p") && Cli.page!=1) Cli.page--;
        else if(option.equals("b")) state=State.MANAGE_PRODUCTS;
        else if(option.equals("a")) state=State.ADD_PRODUCT;
        else if(option.charAt(0) == 'd') {
            try{
                int num=Integer.parseInt(option.substring(1));
                int productId=productArrayList.get(num-1).getId();
                deleteProduct(productId);
                Error.setError(State.NO_ERROR);
            }catch (Exception e){
                Error.setError(State.PRODUCTS_CRUD);
            }
        } else if(option.charAt(0) == 'u') {
            try{
                int num=Integer.parseInt(option.substring(1));
                productIdToUpdate=productArrayList.get(num-1).getId();
                state=State.UPDATE_PRODUCT;
                Error.setError(State.NO_ERROR);
            }catch (Exception e){
                Error.setError(State.PRODUCTS_CRUD);
            }
        }
    }
    public void handleAddProduct(Map<String,String> data){
        try{
            int categoryNumber= Integer.parseInt(data.get("category"));
            addProduct(data.get("name"),myDatabase.getCategoryList().get(categoryNumber-1).getName(),data.get("description"),Double.parseDouble(data.get("price")));
            Error.setError(State.NO_ERROR);
            Cli.displayMsg(" Product added successfully! ",true);
            state=State.MANAGE_PRODUCTS;
        }catch (Exception e){
            Error.setError(State.ADD_PRODUCT);
        }
    }
    public void handleUpdateProduct(Map<String,String> data){
        try{
            Product product = new Product();

            if (!data.get("price").isEmpty()){
                double price=Double.parseDouble(data.get("price"));
                product.setPrice(price);
            }if (!data.get("name").isEmpty())
                product.setName(data.get("name"));
            if (!data.get("description").isEmpty())
                product.setDescription(data.get("description"));

            updateProduct(productIdToUpdate,product);
            state=State.MANAGE_PRODUCTS;
            Error.setError(State.NO_ERROR);
        }catch (Exception e){
            Error.setError(State.UPDATE_PRODUCT);
        }
    }
    public State getState() {
        return state;
    }
    public void login(String email, String password) {
        if(myLogin.loginNow(email,password)){
            if(email.equals("admin@gmail.com"))
                state=State.ADMIN_DASHBOARD;
            else if(email.equals("installer@gmail.com"))
                state=State.INSTALLER_DASHBOARD;
            else
                state=State.CUSTOMER_DASHBOARD;

            Error.setError(State.NO_ERROR);
            return;
        }
        Error.setError(State.LOGIN);
    }

    public void signUp(String fullName, String email,String phone ,String password) {
       if(mySignUp.signUpNow(fullName,email,phone,password)){
           state=State.LOGIN;
           Error.setError(State.NO_ERROR);
           return;

       }
        Error.setError(State.SIGNUP);
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

    public void addAppointment(String email, String productName, String carMake, String date) {
        myDatabase.addAppointment(new Appointment(email,productName,carMake,date));
    }

    public Appointment searchAppointment(int id) {
        return myDatabase.searchAppointment(id);
    }

    public void deleteAppointment(int id) {
        Appointment appointment=myDatabase.searchAppointment(id);
        if(appointment!=null)
            myDatabase.deleteAppointment(appointment);
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
    public void viewOrders(String Email){

    }
    public void viewInstallationReq(String Email) {

    }
}
