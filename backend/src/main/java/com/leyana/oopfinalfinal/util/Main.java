package com.leyana.oopfinalfinal.util;
import java.util.Scanner;
import static com.leyana.oopfinalfinal.util.Services.*; // Import static methods from Services class
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * This class represents the main entry point for the Ticketing System application.
 * @EnableAsync enables asynchronous operations within the application (if applicable).
 */
@EnableAsync // Enable asynchronous operations
public class Main {
    public static void main(String[] args) {

        System.out.println("...Welcome to the Ticketing System Application!...");
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        // Create a Configuration object to store system settings
        Configuration config = new Configuration();

        try {
            // Log application startup information
            Logging.log("INFO", "Starting the Ticketing System Application.");
            // Attempt to load configuration from Services (replace loadConfiguration2 with actual method call)
            initializeTicketingSystem(loadConfiguration2(scanner, config));
            // Initialize the ticketing system with loaded or default configuration
            initializeTicketingSystem(config);

        } catch (ConfigurationLoadException | InputValidationException e) {
            // Log error message if configuration loading or validation fails
            Logging.log("ERROR", "Error occurred: " + e.getMessage());
        } finally {
            // Log application shutdown information
            Logging.log("INFO", "Shutting down the Ticketing System application.");
            Logging.close(); // Close the logging resource
        }
    }

    /**
     * A custom exception class for configuration loading errors.
     */
    class ConfigurationLoadException extends Exception {
        public ConfigurationLoadException(String message) {
            super(message);
        }
    }

    /**
     * A custom exception class for input validation errors.
     */
    static class InputValidationException extends Exception {
        public InputValidationException(String message) {
            super(message);
        }
    }
}
