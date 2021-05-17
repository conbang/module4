package com.example.module4.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Cart implements Serializable {
    private static final long serialVersionUID = 3194004433809527184L;


    private Map<Product,Integer> products = new HashMap<>();

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }

    public Cart() {
    }

    public Cart(Map<Product,Integer> products) {
        this.products = products;
    }

    public Map<Product,Integer> getProducts() {
        return products;
    }

    private boolean checkItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return true;
            }
        }
        return false;
    }

    private Map.Entry<Product, Integer> selectItemInCart(Product product){
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if(entry.getKey().getId().equals(product.getId())){
                return entry;
            }
        }
        return null;
    }

    public void addProduct(Product product){
        if (!checkItemInCart(product)){
            products.put(product,1);
        } else {
            Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
            Integer newQuantity = itemEntry.getValue() + 1;
            products.replace(itemEntry.getKey(),newQuantity);
        }
    }
    public void minusProduct(Product product){
        Map.Entry<Product, Integer> itemEntry = selectItemInCart(product);
        Integer newQuantity = itemEntry.getValue();
        if (newQuantity >=1) {
            newQuantity -= 1;
            products.replace(itemEntry.getKey(), newQuantity);
        }if (newQuantity ==0){
            products.remove(product);
    }}

    public Integer countProductQuantity(){
        Integer productQuantity = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            productQuantity += entry.getValue();
        }
        return productQuantity;
    }

    public Integer countItemQuantity(){
        return products.size();
    }

    public Float countTotalPayment(){
        float payment = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            payment += entry.getKey().getPrice() * (float) entry.getValue();
        }
        return payment;
    }
}
