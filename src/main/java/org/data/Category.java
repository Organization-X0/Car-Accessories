package org.data;

import java.util.ArrayList;

public class Category {
    String name;
    private final ArrayList<Product> productArrayList;
    public Category(String name){
        this.name=name;
        productArrayList=new ArrayList<Product>();
    }
    public void addProduct(Product product) {
        product.setId(Product.getNextId());
        productArrayList.add(product);
    }
    public Product searchOneProduct(int id){
        return productArrayList.stream()
                .filter(product -> product.getId()==(id))
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
