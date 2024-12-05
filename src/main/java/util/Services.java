package util;

import objects.Customer;
import objects.TicketPool;
import objects.Vendor;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Services {
    public static boolean loadConfiguration(Scanner scanner, Configuration config) throws Main.ConfigurationLoadException, Main.InputValidationException {
        System.out.print("Load configuration from file? (yes/no): ");
        String choice = scanner.next().toLowerCase();

        if ("yes".equals(choice)) {
            try {
                Configuration loadedConfig = Configuration.loadFromFile("config.json");
                System.out.println("Previous Configuration Details: ");
                System.out.println(loadedConfig);
                Logging.log("INFO", "Configuration loaded successfully: " + loadedConfig);

                System.out.print("Do you want to use the ticket booking application? (yes/no): ");
                String useAppChoice = scanner.next().toLowerCase();

                if ("yes".equals(useAppChoice)) {
                    // Ask for manual inputs to modify the configuration
                    manualInput(scanner, config);
                    return true;
                } else if ("no".equals(useAppChoice)) {
                    System.out.println("Thank you for using the Ticketing System Application. Goodbye!");
                    System.exit(0); // Gracefully exit the application
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                    return loadConfiguration(scanner, config); // Retry on invalid input
                }
            } catch (RuntimeException e) {
                Logging.log("ERROR", "Failed to load configuration: " + e.getMessage());
                System.out.println("Failed to load configuration, please try again.");
                return loadConfiguration(scanner, config); // Retry on failure
            }
        } else if ("no".equals(choice)) {
            manualInput(scanner, config);
            return true;
        } else {
            System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            return loadConfiguration(scanner, config); // Retry on invalid input
        }
        return false;
    }

    private static void manualInput(Scanner scanner, Configuration config) throws Main.InputValidationException {
        // Input validation using helper methods
        config.setMaxCapacity(getValidInput(scanner, "Enter the maximum capacity of the ticket pool: "));
        config.setNumVendors(getValidInput(scanner, "Enter the number of vendors: "));
        config.setNumCustomers(getValidInput(scanner, "Enter the number of customers: "));
        config.setTicketReleaseRate(getValidInput(scanner, "Enter the ticket release rate: "));
        config.setCustomerRetrieveRate(getValidInput(scanner, "Enter the customer retrieval rate: "));
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
    }

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

    public static void initializeTicketingSystem(Configuration config) {
        try {
            TicketPool ticketPool = new TicketPool(config.getMaxCapacity());
            Logging.log("INFO", "Ticket pool initialized with capacity: " + config.getMaxCapacity());

            // Use threads for vendors and customers
            for (int i = 0; i < config.getNumVendors(); i++) {
                int vendorId = i + 1;
                new Thread(new Vendor(vendorId, config.getTicketReleaseRate(), ticketPool, config.getTotalTicketsPerVendor())).start();
                Logging.log("INFO", "Vendor thread started: Vendor " + vendorId);
            }

            for (int i = 0; i < config.getNumCustomers(); i++) {
                int customerId = i + 1;
                new Thread(new Customer(customerId, ticketPool, config.getCustomerRetrieveRate(), config.getQuantity())).start();
                Logging.log("INFO", "Customer thread started: Customer " + customerId);
            }
        } catch (Exception e) {
            Logging.log("ERROR", "Error initializing the ticketing system: " + e.getMessage());
        }
    }

    // Custom exception classes


}
