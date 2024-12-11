package com.leyana.oopfinalfinal.services;

import org.springframework.stereotype.Service;
import com.leyana.oopfinalfinal.util.Configuration;
import com.leyana.oopfinalfinal.util.Logging;

import static com.leyana.oopfinalfinal.util.Services.initializeTicketingSystem;

@Service
public class ConfigurationService {

    private static final String CONFIG_FILE = "config.json";

    // Retrieve configuration
    public Configuration getConfiguration() {
        try {
            return Configuration.loadFromFile(CONFIG_FILE);
        } catch (RuntimeException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return new Configuration();
        }
    }

    // Save configuration
    public void saveConfiguration(Configuration configuration) {
        try {
            configuration.saveToFile(CONFIG_FILE);
        } catch (RuntimeException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
            throw e;
        }
    }

    // Run the configuration simulation
    public void runConfiguration(Configuration configuration) {
        try {
            // Validate and initialize the ticketing system
            Logging.log("INFO", "Starting ticketing system simulation...");
            initializeTicketingSystem(configuration);
        } catch (Exception e) {
            Logging.log("ERROR", "Simulation failed: " + e.getMessage());
            throw new RuntimeException("Simulation failed", e);
        }
    }
}
