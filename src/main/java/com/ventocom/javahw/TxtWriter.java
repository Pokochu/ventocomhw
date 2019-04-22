package com.ventocom.javahw;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class TxtWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtWriter.class);
    private static final String OUTPUT = "src/main/resources/osszeg.txt";

    private CustomerUtility utility;

    public TxtWriter(CustomerUtility utility) {
        this.utility = utility;
    }

    public void writeToTxt(List<Customer> customers) {

        Path path = Paths.get(OUTPUT);
        try(BufferedWriter writer = Files.newBufferedWriter(path)) {
            for(Customer customer : customers) {
                writer.write(customer.getNumber() + ": " + utility.getFullPayment(customer));
                writer.newLine();
            }
        } catch (IOException e) {
            LOGGER.error("Cannot write to file: {}", path);
        }
    }
}
