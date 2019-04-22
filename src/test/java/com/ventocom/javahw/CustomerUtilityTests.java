package com.ventocom.javahw;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class CustomerUtilityTests {

    private static final String THREE_CUSTOMER_FILE= "src/test/resources/3customer.txt";

    private CustomerUtility utility;
    private TxtReader reader;
    private List<Customer> listOfThree;

    @BeforeClass
    public void setup() {
        utility = new CustomerUtility();
        reader = new TxtReader();
        listOfThree = reader.readTxtFile(THREE_CUSTOMER_FILE);
    }

    @DataProvider()
    public static Object[][] productNumbers() {
        return new Object[][]{
                {0, 0},
                {1, 500},
                {2, 950},
                {3, 1350},
                {4, 1750},
                {10, 4150}
        };
    }

    @Test(dataProvider = "productNumbers")
    public void shouldReturnWithCorrectAmountToPay(int productNum, int expectedAmount) {
        int amountToPay = utility.getAmountToPay(productNum);
        assertEquals(amountToPay, expectedAmount);
    }

    @DataProvider()
    public static Object[][] inputFullPayment() {
        return new Object[][]{
                {1, 500},
                {2, 3900},
                {3, 2300}
        };
    }

    @Test(dataProvider = "inputFullPayment")
    public void shouldCalculateFullPayment(int customerNumber, int expectedFullPayment) {
        int actualFullPayment = utility.getFullPayment(listOfThree.get(customerNumber - 1));
        assertEquals(actualFullPayment, expectedFullPayment);
    }

    @DataProvider()
    public static Object[][] inputProductNumbers() {
        return new Object[][]{
                {1, 1},
                {2, 8},
                {3, 5}
        };
    }

    @Test(dataProvider = "inputProductNumbers")
    public void shouldReturnTheNumOfProductsBought(int customerNumber, int expectedProductNum) {
        int actualProductsNum = utility.getAmountOfProducts(listOfThree.get(customerNumber - 1));
        assertEquals(actualProductsNum, expectedProductNum);
    }

    @DataProvider()
    public static Object[][] inputProductNames() {
        return new Object[][]{
                {"csavarkulcs", 1},
                {"szatyor", 2},
                {"cica", 0}
        };
    }

    @Test(dataProvider = "inputProductNames")
    public void shouldReturnWithCorrectOccurence(String productname, int expectedOccurencePos) {
        int actualPos = utility.getAllOccurence(listOfThree, productname);
        assertEquals(actualPos, expectedOccurencePos);
    }

    @DataProvider()
    public static Object[][] inputProductNamesForFirst() {
        return new Object[][]{
                {"toll", 1},
                {"szatyor", 2},
                {"cica", 0}
        };
    }

    @Test(dataProvider = "inputProductNamesForFirst")
    public void shouldReturnWithCorrectFirstOccurence(String productname, int expectedOccurencePos) {
        int actualPos = utility.getFirstOccurence(listOfThree, productname);
        assertEquals(actualPos, expectedOccurencePos);
    }

    @DataProvider()
    public static Object[][] inputProductNamesForLast() {
        return new Object[][]{
                {"toll", 2},
                {"szatyor", 3},
                {"cica", 0}
        };
    }

    @Test(dataProvider = "inputProductNamesForLast")
    public void shouldReturnWithCorrectLastOccurence(String productname, int expectedOccurencePos) {
        int actualPos = utility.getLastOccurence(listOfThree, productname);
        assertEquals(actualPos, expectedOccurencePos);
    }
}
