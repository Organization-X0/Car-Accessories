package org.data;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private final ArrayList<User> usersList;
    private final ArrayList<Category> categoryList;
    private final ArrayList<Appointment> requestedAppointmentsList;
    private final ArrayList<Appointment> approvedAppointmentArrayList;
    public DataBase(){

        usersList= new ArrayList<>();
        usersList.add(new User("user1","user1@gmail.com","u123","0599123450"));
        usersList.add(new User("user2","user2@gmail.com","u123","0599123451"));
        usersList.add(new User("user3","user3@gmail.com","u123","0599123452"));
        usersList.add(new User("admin","admin@gmail.com","a123","0123456783"));
        usersList.add(new User("installer","installer@gmail.com","i123","0123456784"));


        Product.resetLastId();
        categoryList= new ArrayList<>();

        categoryList.add(new Category("Interior"));
        categoryList.add(new Category("Exterior"));
        categoryList.add(new Category("Electronics"));
        categoryList.get(0).addProduct(new Product("item1",categoryList.get(0).getName(),"item1 is good.",1.3,true));
        categoryList.get(0).addProduct(new Product("item2",categoryList.get(0).getName(),"item2 is good.",5.3,false));
        categoryList.get(0).addProduct(new Product("item3",categoryList.get(0).getName(),"item3 is good.",2.3,true));
        categoryList.get(0).addProduct(new Product("item4",categoryList.get(0).getName(),"item4 is good.",1.3,true));

        categoryList.get(1).addProduct(new Product("item5",categoryList.get(1).getName(),"item5 is good.",1.3,true));
        categoryList.get(1).addProduct(new Product("item6",categoryList.get(1).getName(),"item6 is good.",1.3,true));
        categoryList.get(1).addProduct(new Product("item7",categoryList.get(1).getName(),"item7 is good.",2.4,true));
        categoryList.get(1).addProduct(new Product("item8",categoryList.get(1).getName(),"item8 is good.",6.6,true));

        categoryList.get(2).addProduct(new Product("item9",categoryList.get(2).getName(),"item9 is good.",7.3,true));
        categoryList.get(2).addProduct(new Product("item10",categoryList.get(2).getName(),"item10 is good.",0.3,true));
        categoryList.get(2).addProduct(new Product("item11",categoryList.get(2).getName(),"item11 is good.",2.4,true));
        categoryList.get(2).addProduct(new Product("item12",categoryList.get(2).getName(),"item12 is good.",6.6,true));

        Appointment.resetLastId();
        requestedAppointmentsList = new ArrayList<>();
        Appointment appointment=new Appointment("user1@gmail.com","item1","BMW","2023-10-5",Time.T8_9);
        addAppointment(appointment);

        approvedAppointmentArrayList= new ArrayList<>();
        appointment=new Appointment("user2@gmail.com","item2","TOYOTA","2023-10-20",Time.T10_11);
        approvedAppointmentArrayList.add(appointment);


    }
    public void addUser(User user){
        usersList.add(user);
    }
    public void deleteAccount(User user){
        usersList.remove(user);
    }
    public User searchAccount(String email){
        return usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    public List<User> getCustomerList(){
        ArrayList<User> customers=new ArrayList<>();
        usersList.forEach(user->{
           if(!user.getEmail().equals("admin@gmail.com") && !user.getEmail().equals("installer@gmail.com")){
               customers.add(user);
           }
        });
        return customers;
    }
    public void addCategory(Category category){
        categoryList.add(category);
    }
    public void deleteCategory(Category category){
        categoryList.remove(category);
    }
    public Category searchCategory(String name){
        return categoryList.stream()
                .filter(category -> category.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public List<Category> getCategoryList(){
        return categoryList;
    }
    public Product searchOneProduct(int id){
        return categoryList.stream()
                .flatMap(category -> category.getProductsList().stream())
                .filter(product -> product.getId()==(id))
                .findFirst()
                .orElse(null);
    }
    public List<Product> searchProducts(String name){
        return categoryList.stream()
            .flatMap(category -> category.getProductsList().stream())
            .filter(product -> product.getName().equals(name))
            .toList();
    }
    public void deleteProduct(Product productDelete){
        categoryList.stream()
                .filter(category -> category.getName().equals(productDelete.getCategory()))
                .findFirst().ifPresent(categoryObj -> categoryObj.deleteProduct(productDelete));
    }
    public List<Product> getAllProducts(){
        ArrayList<Product> allProducts=new ArrayList<>();
        categoryList.forEach(category -> allProducts.addAll(category.getProductsList()));
        return allProducts;
    }
    public List<Appointment> searchAppointmentsByDate(String date){
        return requestedAppointmentsList.stream().filter(appointment -> appointment.getDate().equals(date))
                .toList();
    }
    public List<Appointment> getRequestedAppointmentsList(){
        return requestedAppointmentsList;
    }
    public void addAppointment(Appointment appointment){
        appointment.setId(Appointment.getNextId());
        requestedAppointmentsList.add(appointment);
    }
    public void addApprovedAppointment(Appointment appointment) {
        approvedAppointmentArrayList.add(appointment);
    }
    public List<Appointment> getApprovedAppointmentArrayList(){
        return approvedAppointmentArrayList;
    }
    public Appointment searchAppointment(int id){
        return requestedAppointmentsList.stream()
                .filter(appointment -> appointment.getId()==id)
                .findFirst()
                .orElse(null);
    }
    public Appointment searchApprovedAppointment(int id){
        return approvedAppointmentArrayList.stream()
                .filter(appointment -> appointment.getId()==(id))
                .findFirst()
                .orElse(null);
    }
    public void deleteAppointment(Appointment appointmentDelete){
        requestedAppointmentsList.remove(appointmentDelete);
    }
    public void deleteApprovedAppointment(Appointment appointmentDelete){
        approvedAppointmentArrayList.remove(appointmentDelete);
    }

}
