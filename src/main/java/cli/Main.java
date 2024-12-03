package cli;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        System.out.println("Load configuration from file? (yes/no): ");
        String choice = scanner.next().toLowerCase();

        if ("yes".equals(choice)) {
            try{
                config = Configuration.loadFromFile("config.json");
                System.out.println("Configuration loaded successfully: " + config);

            } catch (RuntimeException e){
                System.out.println("Failed to load configuration: " + e.getMessage());
                System.out.println("Proceeding with manual input");
                return;
            }
        } else {
            config = new Configuration();

            System.out.println("Enter the maximum capacity of the ticket pool: ");
            config.setMaxCapacity(scanner.nextInt());

            System.out.print("Enter the number of vendors: ");
            config.setNumVendors(scanner.nextInt());

            System.out.print("Enter the number of customers: ");
            config.setNumCustomers(scanner.nextInt());

            System.out.print("Enter the ticket release rate (seconds): ");
            config.setTicketReleaseRate(scanner.nextInt());

            System.out.print("Enter the customer retrieval rate (seconds): ");
            config.setCustomerRetrieveRate(scanner.nextInt());

            System.out.print("Enter the total tickets each vendor will add: ");
            config.setTotalTicketsPerVendor(scanner.nextInt());

            System.out.print("Enter the number of tickets that each customer will buy: ");
            config.setQuantity(scanner.nextInt());

            System.out.println("Save configuration to a file? (yes/no): ");
            if ("yes".equals(scanner.next().toLowerCase())) {

                try {
                    config.saveToFile("config.json");
                } catch (RuntimeException e) {
                    System.err.println("Failed to save configuration: " + e.getMessage());
                }
            }
        }


        TicketPool ticketPool = new TicketPool(config.getMaxCapacity());

        Vendor[] vendors = new Vendor[config.getNumVendors()];
        for (int i = 0; i < vendors.length; i++){
            vendors[i] = new Vendor(i + 1, config.getTicketReleaseRate(), ticketPool, config.getTotalTicketsPerVendor());
            Thread vendorThread = new Thread(vendors[i], "Vendor ID: " + (i + 1));
            vendorThread.start();
        }

        Customer[] customers = new Customer[config.getNumCustomers()];
        for (int i = 0; i < customers.length; i++){
            customers[i] = new Customer(i + 1, ticketPool, config.getCustomerRetrieveRate(), config.getQuantity());
            Thread customerThread = new Thread(customers[i], "Vendor ID: " + (i + 1));
            customerThread.start();
        }


    }
}



