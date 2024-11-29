package cli;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the maximum capacity of the ticket pool");
        int maxCapacity = scanner.nextInt();

        System.out.print("Enter the number of vendors: ");
        int numVendors = scanner.nextInt();

        System.out.print("Enter the number of customers: ");
        int numCustomers = scanner.nextInt();

        System.out.print("Enter the ticket release rate (seconds): ");
        int ticketsReleaseRate = scanner.nextInt();

        System.out.print("Enter the customer retrieval rate (seconds): ");
        int customerRetrieveRate = scanner.nextInt();

        System.out.print("Enter the total tickets each vendor will add: ");
        int totalTicketsPerVendor = scanner.nextInt();


        TicketPool ticketPool = new TicketPool(maxCapacity);

        Vendor[] vendors = new Vendor[numVendors];
        for (int i = 0; i < vendors.length; i++){
            vendors[i] = new Vendor(i + 1, ticketsReleaseRate,ticketPool,totalTicketsPerVendor);
            Thread vendorThread = new Thread(vendors[i], "Vendor ID: " + (i + 1));
            vendorThread.start();
        }

        Customer[] customers = new Customer[numCustomers];
        for (int i = 0; i < customers.length; i++){
            customers[i] = new Customer(i + 1, ticketPool, customerRetrieveRate, maxCapacity / 2);
            Thread customerThread = new Thread(customers[i], "Vendor ID: " + (i + 1));
            customerThread.start();
        }


    }
}



