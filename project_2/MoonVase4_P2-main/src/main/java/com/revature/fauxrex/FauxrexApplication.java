package com.revature.fauxrex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

/**
 * DRIVER FOR FAUXREX
 */
@SpringBootApplication
public class FauxrexApplication {

	/**
	 * Main method to run Spring Faurex application
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		SpringApplication.run(FauxrexApplication.class, args);
	}

}
