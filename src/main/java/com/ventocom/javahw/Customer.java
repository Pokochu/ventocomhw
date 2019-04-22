package com.ventocom.javahw;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Map;


public class Customer {

    private int number;
    private Map<String, Integer> products;

    public Customer() {
    }

    public Customer(int number, Map<String, Integer> products) {
        this.number = number;
        this.products = products;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public Integer getAmountOfProduct(String productName) {
        return getProducts().get(productName);
    }

    public void setProduct(String productName, int amount) {
        products.put(productName, amount);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
