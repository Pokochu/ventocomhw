package com.ventocom.javahw;

import static org.testng.Assert.assertTrue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TxtWriterTests {

    private static final Logger LOGGER = LoggerFactory.getLogger(TxtWriterTests.class);

    private static final String THREE_CUSTOMER_FILE = "src/test/resources/3customer.txt";
    private static final String OUTPUT = "src/main/resources/osszeg.txt";

    private CustomerUtility utility;
    private TxtWriter writer;
    private TxtReader reader;
    private List<Customer> customers;

    @BeforeClass
    public void setUp() {
        utility = new CustomerUtility();
        writer = new TxtWriter(utility);
        reader = new TxtReader();
    }

    @AfterClass
    public void tearDown() {
        try {
            Files.delete(Paths.get(OUTPUT));
            LOGGER.info("Test output file successfully deleted!");
        } catch (IOException e) {
            LOGGER.error("Cannot delete testOuput.txt file!!");
            e.printStackTrace();
        }
    }

    @Test
    public void shouldCreateCorrectOutPutFileToTestFolder() {
        customers = reader.readTxtFile(THREE_CUSTOMER_FILE);
        writer.writeToTxt(customers);
        assertTrue(Files.exists(Paths.get(OUTPUT)));
    }


}
