package com.leyana.oopfinalfinal.util;
import com.leyana.oopfinalfinal.objects.Customer;
import com.leyana.oopfinalfinal.objects.TicketPool;
import com.leyana.oopfinalfinal.objects.Vendor;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class provides utility methods for the Ticketing System application,
 * including configuration loading, manual input handling, and initialization logic.
 */
public class Services {

    /**
     * Loads the configuration from a file or prompts for manual input.
     * @param scanner the Scanner object for user input
     * @param config the Configuration object to be populated
     * @return the loaded or manually entered configuration
     * @throws Main.ConfigurationLoadException if configuration loading fails
     * @throws Main.InputValidationException if user input is invalid
     */
    public static Configuration loadConfiguration2(Scanner scanner, Configuration config) throws Main.ConfigurationLoadException, Main.InputValidationException {
        System.out.print("Load configuration from file? (yes/no): ");
        String choice = scanner.next().toLowerCase();
        Configuration loadedConfig;

        if ("yes".equals(choice)) {
            try {
                loadedConfig = Configuration.loadFromFile("config.json");
                System.out.println("Previous Configuration Details: ");
                System.out.println(loadedConfig);
                Logging.log("INFO", "Configuration loaded successfully: " + loadedConfig);

                System.out.print("Do you want to use the selected Configuration? (yes/no): ");
                String useAppChoice = scanner.next().toLowerCase();

                if ("yes".equals(useAppChoice)) {
                    // Offer chance to modify loaded config with manual input (optional)
                    return loadedConfig;
                } else if ("no".equals(useAppChoice)) {
                    System.out.println("Thank you for using the Ticketing System Application. Goodbye!");
                    System.exit(0); // Gracefully exit the application
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    return loadConfiguration2(scanner, config); // Retry on invalid input
                }
            } catch (RuntimeException e) {
                Logging.log("ERROR", "Failed to load configuration: " + e.getMessage());
                System.out.println("Failed to load configuration, please try again.");
                return loadConfiguration2(scanner, config); // Retry on failure
            }
        } else if ("no".equals(choice)) {

            return manualInput(scanner, config);
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            return loadConfiguration2(scanner, config); // Retry on invalid input
        }
        return loadedConfig;
    }

    /**
     * Prompts the user for configuration details and populates the Configuration object.
     * @param scanner the Scanner object for user input
     * @param config the Configuration object to be populated
     * @return the configuration object with manually entered values
     * @throws Main.InputValidationException if user input is invalid
     */
    private static Configuration manualInput(Scanner scanner, Configuration config) throws Main.InputValidationException {
        // Input validation using helper methods
        config.setMaxCapacity(getValidInput(scanner, "Enter the maximum capacity of the ticket pool: "));
        config.setNumVendors(getValidInput(scanner, "Enter the number of vendors: "));
        config.setNumCustomers(getValidInput(scanner, "Enter the number of customers: "));

        // Validate and set ticket release rate
        int ticketReleaseRate;
        while (true) {
            System.out.print("Enter the ticket release rate: ");
            try {
                ticketReleaseRate = scanner.nextInt();
                if (ticketReleaseRate > 0 && ticketReleaseRate <= config.getMaxCapacity()) {
                    config.setTicketReleaseRate(ticketReleaseRate);
                    break;
                } else {
                    System.out.println("Ticket release rate must be greater than 0 and less than or equal to the maximum capacity, please try again.");
                }
            } catch (InputMismatchException e) {
                scanner.next(); // clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Validate and set customer retrieval rate
        int customerRetrieveRate;
        while (true) {
            System.out.print("Enter the customer retrieval rate: ");
            try {
                customerRetrieveRate = scanner.nextInt();
                if (customerRetrieveRate > 0 && customerRetrieveRate <= config.getMaxCapacity()) {
                    config.setCustomerRetrieveRate(customerRetrieveRate);
                    break;
                } else {
                    System.out.println("Customer retrieval rate must be greater than 0 and less than or equal to the maximum capacity, please try again.");
                }
            } catch (InputMismatchException e) {
                scanner.next(); // clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        config.setTotalTicketsPerVendor(getValidInput(scanner, "Enter the total tickets each vendor will add: "));
        config.setQuantity(getValidInput(scanner, "Enter the number of tickets that each customer will buy: "));

        System.out.println("Save configuration to a file? (yes/no): ");
        String saveChoice = scanner.next();
        while (!"yes".equalsIgnoreCase(saveChoice) && !"no".equalsIgnoreCase(saveChoice)) {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            saveChoice = scanner.next();
        }

        if ("yes".equalsIgnoreCase(saveChoice)) {
            try {
                config.saveToFile("config.json");
                System.out.println("Configuration saved to file: config.json");
            } catch (RuntimeException e) {
                System.err.println("Failed to save configuration: " + e.getMessage());
            }
        }

        return config;
    }

    /**
     * A method to validate inputs
     * @param scanner the Scanner object for user input
     * @param prompt
     * @return the input
     * @throws Main.InputValidationException
     */
    private static int getValidInput(Scanner scanner, String prompt) throws Main.InputValidationException {
        int input = -1;
        while (input == -1) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                if (input <= 0) {
                    throw new Main.InputValidationException("Input must be a positive integer.");
                }
            } catch (InputMismatchException e) {
                scanner.next(); // clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (Main.InputValidationException e) {
                Logging.log("ERROR", e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    /**
     * Initializes the ticketing system with the given configuration.
     * @param config the configuration object containing system parameters
     */
    public static void initializeTicketingSystem(Configuration config) {
        try {
            // Create a new ticket pool with the specified maximum capacity
            TicketPool ticketPool = new TicketPool(config.getMaxCapacity());
            Logging.log("INFO", "Ticket pool initialized with capacity: " + config.getMaxCapacity());

            // Create and start vendor threads
            for (int i = 0; i < config.getNumVendors(); i++) {
                int vendorId = i + 1;
                new Thread(new Vendor(vendorId, config.getTicketReleaseRate(), ticketPool, config.getTotalTicketsPerVendor())).start();
                Logging.log("INFO", "Vendor thread started: Vendor " + vendorId);
            }

            // Create and start customer threads
            for (int i = 0; i < config.getNumCustomers(); i++) {
                int customerId = i + 1;
                new Thread(new Customer(customerId, ticketPool, config.getCustomerRetrieveRate(), config.getQuantity())).start();
                Logging.log("INFO", "Customer thread started: Customer " + customerId);
            }
        } catch (Exception e) {
            Logging.log("ERROR", "Error initializing the ticketing system: " + e.getMessage());
        }
    }
}
