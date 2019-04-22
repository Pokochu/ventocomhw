package com.ventocom.javahw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class JavahwApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavahwApplication.class, args);

		TxtReader reader = new TxtReader();
		CustomerUtility utility = new CustomerUtility();
		TxtWriter writer = new TxtWriter(utility);

		System.out.println("1. feladat:");
		List<Customer> customers = reader.readTxtFile("src/main/resources/penztar.txt");
		System.out.println("Fájl beolvasás sikeres!");

		System.out.println("2. feladat:");
		System.out.println("Vásárlók száma: " + customers.size());

		System.out.println("3. feladat:");
		System.out.println("Az első vásárló " + utility.getAmountOfProducts(customers.get(0)) + " db terméket vett.");

		System.out.println("4. feladat:");
		System.out.println("Adja meg egy vásárlás sorszámát: ");
		Scanner inputScanner = new Scanner(System.in);
		int userNum = inputScanner.nextInt();
		System.out.println("Adjon meg egy vásárolt terméket: ");
		String userProduct = inputScanner.next();
		System.out.println("Adja meg egy vásárlási darabszámot: ");
		int userAmount = inputScanner.nextInt();

		System.out.println("5. feladat:");
		System.out.println("Az első vásárlás sorszáma: " + (utility.getFirstOccurence(customers, userProduct) == 0 ? "Nem volt ilyen vásárlás!" : utility.getFirstOccurence(customers, userProduct)));
		System.out.println("Az utolsó vásárlás sorszáma: " + (utility.getLastOccurence(customers, userProduct) == 0 ? "Nem volt ilyen vásárlás!" : utility.getLastOccurence(customers, userProduct)));
		System.out.println(utility.getAllOccurence(customers, userProduct) + " vásárlás során vettek belőle.");

		System.out.println("6. feladat:");
		System.out.println(userAmount + " db vásárlása esetén fizetendő: " + utility.getAmountToPay(userAmount));

		System.out.println("7. feladat:");
		utility.writeCustomerBasket(customers.get(userNum - 1));

		writer.writeToTxt(customers);
		System.out.println("osszeg.txt fájl elkészítése sikerült!");
	}
}
