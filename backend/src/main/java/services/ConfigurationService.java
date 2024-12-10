package services;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import util.Configuration;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ConfigurationService {

    private static final String CONFIG_FILE = "config.json";

    // Retrieve configuration
    public Configuration getConfiguration() {
        try {
            return Configuration.loadFromFile(CONFIG_FILE);
        } catch (RuntimeException e) {
            // Return default configuration and log the error
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
}
