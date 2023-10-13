package org.Data;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class DataBase {
    private final ArrayList<User> usersList;
    private final ArrayList<Category> categoryList;
    private final ArrayList<Appointment> appointmentsList;
    public DataBase(){
        usersList=new ArrayList<User>();
        usersList.add(new User("user1","user1@gmail.com","u123","0599123456"));
        usersList.add(new User("user2","user2@gmail.com","u123","0599123456"));
        usersList.add(new User("user3","user3@gmail.com","u123","0599123456"));
        usersList.add(new User("admin","admin@gmail.com","a123","0123456789"));

        categoryList=new ArrayList<Category>();

        categoryList.add(new Category("Interior"));
        categoryList.add(new Category("Exterior"));
        categoryList.add(new Category("Electronics"));
        categoryList.get(0).addProduct(new Product("item1",categoryList.get(0).getName(),"Don't buy please.",1.3,true));
        categoryList.get(0).addProduct(new Product("item2",categoryList.get(0).getName(),"Don't buy please.",5.3,true));
        categoryList.get(0).addProduct(new Product("item3",categoryList.get(0).getName(),"Don't buy please.",2.3,true));
        categoryList.get(0).addProduct(new Product("item4",categoryList.get(0).getName(),"Don't buy please.",1.3,true));

        categoryList.get(1).addProduct(new Product("item1",categoryList.get(1).getName(),"Don't buy please.",1.3,true));
        categoryList.get(1).addProduct(new Product("item2",categoryList.get(1).getName(),"Don't buy please.",1.3,true));
        categoryList.get(1).addProduct(new Product("item3",categoryList.get(1).getName(),"Don't buy please.",2.4,true));
        categoryList.get(1).addProduct(new Product("item4",categoryList.get(1).getName(),"Don't buy please.",6.6,true));

        categoryList.get(2).addProduct(new Product("item1",categoryList.get(2).getName(),"Don't buy please.",7.3,true));
        categoryList.get(2).addProduct(new Product("item2",categoryList.get(2).getName(),"Don't buy please.",0.3,true));
        categoryList.get(2).addProduct(new Product("item3",categoryList.get(2).getName(),"Don't buy please.",2.4,true));
        categoryList.get(2).addProduct(new Product("item4",categoryList.get(2).getName(),"Don't buy please.",6.6,true));

        appointmentsList = new ArrayList<Appointment>();
        Appointment appointment=new Appointment("user1@gmail.com","item1","BMW","2023-10-5");
        appointment.setId(0);
        appointmentsList.add(appointment);
        appointment=new Appointment("user2@gmail.com","item2","TOYOTA","2023-10-20");
        appointment.setId(1);
        appointmentsList.add(appointment);
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
    public ArrayList<User> getCustomerList(){
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
    public ArrayList<Category> getCategoryList(){
        return categoryList;
    }
    public Product searchOneProduct(int id){
        return categoryList.stream()
                .flatMap(category -> category.productArrayList.stream())
                .filter(product -> product.getId()==(id))
                .findFirst()
                .orElse(null);
    }
    public ArrayList<Product> searchProducts(String name){
        return (ArrayList<Product>) categoryList.stream()
            .flatMap(category -> category.productArrayList.stream())
            .filter(product -> product.getName().equals(name))
            .collect(Collectors.toList());
    }
    public void deleteProduct(Product productDelete){
        categoryList.stream()
                .filter(category -> category.getName().equals(productDelete.getCategory()))
                .findFirst().ifPresent(categoryObj -> categoryObj.deleteProduct(productDelete));
    }
    public ArrayList<Product> getAllProducts(){
        ArrayList<Product> allProducts=new ArrayList<Product>();
        allProducts.addAll(categoryList.get(0).productArrayList);
        allProducts.addAll(categoryList.get(1).productArrayList);
        allProducts.addAll(categoryList.get(2).productArrayList);
        return allProducts;
    }
    public void addAppointment(Appointment appointment){
        if(!appointmentsList.isEmpty()){
            appointment.setId((appointmentsList.get(appointmentsList.size()-1).getId())+1);
        }
        appointmentsList.add(appointment);
    }
    public Appointment searchAppointment(int id){
        return appointmentsList.stream()
                .filter(appointment -> appointment.getId()==id)
                .findFirst()
                .orElse(null);
    }
    public void deleteAppointment(Appointment appointmentDelete){
        appointmentsList.remove(appointmentDelete);
    }
}
