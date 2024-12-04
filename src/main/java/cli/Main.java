package cli;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        try {
            Logging.log("INFO", "Starting the Ticketing System Application.");
            loadConfiguration(scanner, config);
            initializeTicketingSystem(config);
        } catch (ConfigurationLoadException | InputValidationException e) {
            Logging.log("ERROR", "Error occurred: " + e.getMessage());
        } finally {
            Logging.log("INFO", "Shutting down the Ticketing System application.");
            Logging.close();
        }
    }

    private static void loadConfiguration(Scanner scanner, Configuration config) throws ConfigurationLoadException, InputValidationException {
        String choice;
        while (true) {
            System.out.print("Load configuration from file? (yes/no): ");
            choice = scanner.next().toLowerCase();  // Convert input to lowercase

            if ("yes".equals(choice) || "no".equals(choice)) {
                break;  // Valid input, exit the loop
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }

        if ("yes".equals(choice)) {
            try {
                config = Configuration.loadFromFile("config.json");
                System.out.println("Previous Configuration Details: ");
                System.out.println(config);
                Logging.log("INFO", "Configuration loaded successfully: " + config);
                System.out.println("Configuration loaded successfully.");
                manualInput(scanner, config);

            } catch (RuntimeException e) {
                Logging.log("ERROR", "Failed to load configuration: " + e.getMessage());
                System.out.println("Failed to load configuration, please try again");
                loadConfiguration(scanner, config);
            }
        } else {
            manualInput(scanner, config);
        }
    }

    private static void manualInput(Scanner scanner, Configuration config) throws InputValidationException {
        // Input validation using helper methods
        config.setMaxCapacity(getValidInput(scanner, "Enter the maximum capacity of the ticket pool: "));
        config.setNumVendors(getValidInput(scanner, "Enter the number of vendors: "));
        config.setNumCustomers(getValidInput(scanner, "Enter the number of customers: "));
        config.setTicketReleaseRate(getValidInput(scanner, "Enter the ticket release rate: "));
        config.setCustomerRetrieveRate(getValidInput(scanner, "Enter the customer retrieval rate: "));
        config.setTotalTicketsPerVendor(getValidInput(scanner, "Enter the total tickets each vendor will add: "));
        config.setQuantity(getValidInput(scanner, "Enter the number of tickets that each customer will buy: "));

        System.out.println("Save configuration to a file? (yes/no): ");
        if ("yes".equalsIgnoreCase(scanner.next())) {
            try {
                config.saveToFile("config.json");
            } catch (RuntimeException e) {
                System.err.println("Failed to save configuration: " + e.getMessage());
            }
        }
    }

    private static int getValidInput(Scanner scanner, String prompt) throws InputValidationException {
        int input = -1;
        while (input == -1) {
            System.out.print(prompt);
            try {
                input = scanner.nextInt();
                if (input <= 0) {
                    throw new InputValidationException("Input must be a positive integer.");
                }
            } catch (InputMismatchException e) {
                scanner.next(); // clear the invalid input
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (InputValidationException e) {
                Logging.log("ERROR", e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        return input;
    }

    private static void initializeTicketingSystem(Configuration config) {
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
    class ConfigurationLoadException extends Exception {
        public ConfigurationLoadException(String message) {
            super(message);
        }
    }

    static class InputValidationException extends Exception {
        public InputValidationException(String message) {
            super(message);
        }
    }
}
