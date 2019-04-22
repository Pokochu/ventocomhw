package com.ventocom.javahw;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class TxtReaderTests {

    private static final String THREE_CUSTOMER_FILE= "src/test/resources/3customer.txt";
    private static final String TEN_CUSTOMER_FILE= "src/test/resources/10customer.txt";
    private static final String EMPTY_CUSTOMER_FILE= "src/test/resources/emptyFile.txt";
    private static final String INCORRECT_CUSTOMER_FILE= "src/test/resources/noF.txt";
    private static final String WRONG_PATH = "thisFileDoesntExist.txt";

    private TxtReader reader;

    @BeforeClass
    public void setUp() {
        reader = new TxtReader();
    }

    @DataProvider()
    public static Object[][] inputFiles() {
        return new Object[][]{
                {THREE_CUSTOMER_FILE, 3},
                {TEN_CUSTOMER_FILE, 10},
                {EMPTY_CUSTOMER_FILE, 0},
                {INCORRECT_CUSTOMER_FILE, 0}};
    }

    @Test(dataProvider = "inputFiles")
    public void shouldReturnWithCorrectNumberOfCustomers(String path, int expectedCustomerListSize) {
        List<Customer> customers = reader.readTxtFile(path);
        assertEquals(customers.size(), expectedCustomerListSize);
    }

    @Test
    public void shouldReturnWithNullOnIOException() {
        List<Customer> customers = reader.readTxtFile(WRONG_PATH);
        assertNull(customers);
    }
}
