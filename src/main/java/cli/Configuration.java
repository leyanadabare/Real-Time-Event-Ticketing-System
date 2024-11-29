package cli;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Configuration {

    private int maxCapacity;
    private int numVendors;
    private int numCustomers;
    private int ticketReleaseRate;
    private int customerRetrieveRate;
    private int totalTicketsPerVendor;

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public int getNumVendors() {
        return numVendors;
    }

    public void setNumVendors(int numVendors) {
        this.numVendors = numVendors;
    }

    public int getNumCustomers() {
        return numCustomers;
    }

    public void setNumCustomers(int numCustomers) {
        this.numCustomers = numCustomers;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrieveRate() {
        return customerRetrieveRate;
    }

    public void setCustomerRetrieveRate(int customerRetrieveRate) {
        this.customerRetrieveRate = customerRetrieveRate;
    }

    public int getTotalTicketsPerVendor() {
        return totalTicketsPerVendor;
    }

    public void setTotalTicketsPerVendor(int totalTicketsPerVendor) {
        this.totalTicketsPerVendor = totalTicketsPerVendor;
    }


    public void saveToFile(String filePath) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(this, writer);
            System.out.println("Configuration saved to file: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException("Error saving configuration: " + e.getMessage());
        }
    }

    // Load configuration from a file
    public static Configuration loadFromFile(String filePath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            throw new RuntimeException("Error loading configuration: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Configuration: " +
                "maxCapacity = " + maxCapacity +
                ", numVendors = " + numVendors +
                ", numCustomers = " + numCustomers +
                ", ticketsReleaseRate = " + ticketReleaseRate +
                ", customerRetrieveRate = " + customerRetrieveRate +
                ", totalTicketsPerVendor = " + totalTicketsPerVendor;
    }
}
