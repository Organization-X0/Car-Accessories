package org.data;

import java.util.ArrayList;
import java.util.List;

public class Category {
    String name;
    private final ArrayList<Product> productArrayList;
    public Category(String name){
        this.name=name;
        productArrayList= new ArrayList<>();
    }
    public void addProduct(Product product) {
        product.setId(Product.getNextId());
        productArrayList.add(product);
    }
    public List<Product> getProductsList() {
        return productArrayList;
    }
    public void deleteProduct(Product productDelete){
        productArrayList.remove(productDelete);
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
         this.name=name;
    }
}
