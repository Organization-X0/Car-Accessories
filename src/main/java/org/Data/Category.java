package org.Data;

import java.util.ArrayList;

public class Category {
    String name;
    ArrayList<Product> productArrayList;
    public Category(String name){
        this.name=name;
        productArrayList=new ArrayList<Product>();
    }
    public void addProduct(Product product) {
        productArrayList.add(product);
    }
    public Product searchOneProduct(String id){
        return productArrayList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    public ArrayList<Product> getProductsList() {
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
