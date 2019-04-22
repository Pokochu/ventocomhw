package com.ventocom.javahw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TxtReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtReader.class);
    private static final String DELIMITER = "F";

    public List<Customer> readTxtFile(String filePath) {

        List<Customer> customers = new ArrayList<>();
        Customer customer;
        int customerCounter = 1;
        try(Stream<String> stream = Files.lines(Paths.get(filePath))) {
            customer = new Customer(customerCounter, new HashMap<>());
            List<String> lines = stream.collect(Collectors.toList());
            for(String line : lines) {
                if(line.equals(DELIMITER)) {
                    customers.add(customer);
                    customerCounter += 1;
                    customer = new Customer(customerCounter, new HashMap<>());
                } else {
                    Integer amountOfProduct = customer.getAmountOfProduct(line);
                    customer.setProduct(line, (amountOfProduct==null) ? 1 : amountOfProduct + 1);
                }
            }
        } catch (IOException e) {
            LOGGER.error("Cannot read file from path: {}!", filePath);
            return null;
        }
        return customers;
    }
}
