package org.data;

public class Product {
    private int id;
    private String name;
    private String category;
    private String description;
    private double price;
    private boolean availability;
    private static int lastId;

    public Product(String name, String category, String description, double price, boolean availability) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.availability = availability;
    }
    public Product(){
    }
    public static int getNextId(){
        return ++lastId;
    }
    public static void setLastId(DataBase myDatabase){
        for(Category category:myDatabase.getCategoryList()){
            if(category.getProductsList().get(category.getProductsList().size()-1).getId()>lastId)
                lastId=category.getProductsList().get(category.getProductsList().size()-1).getId();
        }
    }
    public static int getLastId(){
        return lastId;
    }
    public static void resetLastId(){
        lastId=0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
}
