package org.Data;

import org.Car.App;

import java.util.ArrayList;

public class DataBase {
    private final ArrayList<User> usersList;
    private final ArrayList<Category> categoryList;
    private final ArrayList<Appointment> appointmentsList;
    public DataBase(){
        usersList=new ArrayList<User>();
        usersList.add(new User("user1","user1@gmail.com","u123","0599123456"));
        usersList.add(new User("user2","user2@gmail.com","u123","0599123456"));
        usersList.add(new User("user3","user3@gmail.com","u123","0599123456"));

        categoryList=new ArrayList<Category>();
        categoryList.add(new Category("Interior"));
        categoryList.get(0).addProduct(new Product("item1",categoryList.get(0).getName(),"Don't buy please.",1.3,true));
        categoryList.get(0).addProduct(new Product("item2",categoryList.get(0).getName(),"Don't buy please.",5.3,true));
        categoryList.get(0).addProduct(new Product("item3",categoryList.get(0).getName(),"Don't buy please.",2.3,true));

        appointmentsList = new ArrayList<Appointment>();
        Appointment appointment=new Appointment("user1@gmail.com","item1","BMW","2023-10-5");
        appointment.setId(0);
        appointmentsList.add(appointment);
        appointment=new Appointment("user2@gmail.com","item2","TOYOTA","2023-10-20");
        appointment.setId(1);
        appointmentsList.add(appointment);
    }
    public User search(String email){
        return usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    public void addUser(User user){
        usersList.add(user);
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
    public Product searchOneProduct(int id){
        return categoryList.stream()
                .flatMap(category -> category.productArrayList.stream())
                .filter(product -> product.getId()==(id))
                .findFirst()
                .orElse(null);
    }
    public void deleteProduct(Product productDelete){
        categoryList.stream()
                .filter(category -> category.getName().equals(productDelete.getCategory()))
                .findFirst().ifPresent(categoryObj -> categoryObj.deleteProduct(productDelete));
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
    public void deleteAccount(User user){
        usersList.remove(user);
    }
    public User searchAccount(String email){
        return usersList.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
}
