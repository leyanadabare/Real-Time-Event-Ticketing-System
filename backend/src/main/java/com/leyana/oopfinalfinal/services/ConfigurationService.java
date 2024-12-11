package com.leyana.oopfinalfinal.services;

import com.leyana.oopfinalfinal.util.Configuration;
import org.springframework.stereotype.Service;
import com.leyana.oopfinalfinal.util.Logging;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.leyana.oopfinalfinal.util.Services.initializeTicketingSystem;

@Service
public class ConfigurationService {

    private static final String CONFIG_FILE = "config.json";
    private static final String LOG_FILE = "logs/ticketing_system.log";  // Correct log file path
    private final List<String> logMessages = new ArrayList<>();
    private AtomicBoolean isReadingLogs = new AtomicBoolean(false); // Flag to control log reading

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
            Logging.log("INFO", "Starting ticketing system simulation...");
            initializeTicketingSystem(configuration);

            // Start log reading in a separate thread
            isReadingLogs.set(true);
            readLogsRealTime();  // Start reading logs from the file
        } catch (Exception e) {
            Logging.log("ERROR", "Simulation failed: " + e.getMessage());
            throw new RuntimeException("Simulation failed", e);
        }
    }

    // Method to start reading logs in real-time
    public void readLogsRealTime() {
        new Thread(() -> {
            try (BufferedReader reader = new BufferedReader(new FileReader(new File(LOG_FILE)))) {
                String line;
                while (isReadingLogs.get() && (line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        synchronized (logMessages) {
                            logMessages.add(line);  // Add log message to the list
                        }
                        Logging.log("INFO", "Log data: " + line);
                    }
                    Thread.sleep(500);  // Poll every 500ms for new logs
                }
            } catch (IOException | InterruptedException e) {
                System.err.println("Error reading log file: " + e.getMessage());
            }
        }).start();
    }

    // Method to stop reading logs
    public void stopReadingLogs() {
        isReadingLogs.set(false);
    }

    // Optional: Method to check if log reading is active
    public boolean isLogReadingActive() {
        return isReadingLogs.get();
    }

    // Method to fetch logs for the frontend
    public List<String> readLogs() {
        synchronized (logMessages) {
            List<String> logsToReturn = new ArrayList<>(logMessages);
            logMessages.clear();  // Clear logs after sending them to avoid duplicate data
            return logsToReturn;
        }
    }


    // Method to clear the log file
    public void clearLogFile() throws IOException {
        File logFile = new File(LOG_FILE);
        if (logFile.exists()) {
            try (FileWriter fileWriter = new FileWriter(logFile)) {
                fileWriter.write("");  // Clear the file contents
            }
        }
    }
}

