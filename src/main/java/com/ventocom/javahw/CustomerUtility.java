package com.ventocom.javahw;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerUtility {

    /*
     * Returns the number of products bought by the given customer.
     */
    public int getAmountOfProducts(Customer customer) {
        int amountOfProducts = 0;
        for(String product : customer.getProducts().keySet()) {
            amountOfProducts += customer.getAmountOfProduct(product);
        }
        return amountOfProducts;
    }

    /*
     * Returns the amount to pay from a given number of product.
     */
    public int getAmountToPay(int numOfProduct) {
        int payment = 0;
        for(int i = 0; i < numOfProduct; i++) {
            payment += 500 - ((i > 2) ? 2 * 50 : i * 50);
        }
        return payment;
    }

    /*
     * Returns the full payment after a customer's all products.
     */
    public int getFullPayment(Customer customer) {
        int fullPayment = 0;
        for(String product : customer.getProducts().keySet()) {
            fullPayment += getAmountToPay(customer.getAmountOfProduct(product));
        }
        return fullPayment;
    }

    /*
     * Returns the number of customer where the given product is occurence first or 0 if it is not present.
     */
    public int getFirstOccurence(List<Customer> customers, String product) {
        return customers.stream().filter(customer ->
                customer.getProducts().keySet().contains(product)).map(Customer::getNumber).findFirst().orElse(0);
    }

    /*
     * Returns the number of customer where the given product is occurence last or 0 if it is not present.
     */
    public int getLastOccurence(List<Customer> customers, String product) {
        return customers.stream().filter(customer ->
                customer.getProducts().keySet().contains(product))
                .map(Customer::getNumber)
                .reduce((first, second) -> second).orElse(0);
    }

    /*
     * Returns the number of occurences from the customer list by the given product's name.
     */
    public int getAllOccurence(List<Customer> customers, String product) {
        return customers.stream().filter(customer ->
                customer.getProducts().keySet().contains(product))
                .collect(Collectors.toList()).size();
    }

    /*
     * Writes the given customer's backet to to standard output.
     */
    public void writeCustomerBasket(Customer customer) {
        customer.getProducts().keySet().forEach(product -> {
            int db = customer.getAmountOfProduct(product);
            StringBuilder builder = new StringBuilder("");
            builder.append(db)
                    .append(" ")
                    .append(product);

            System.out.println(builder.toString());
        });
    }
}
