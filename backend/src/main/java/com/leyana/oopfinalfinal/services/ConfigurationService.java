package com.leyana.oopfinalfinal.services;
import com.leyana.oopfinalfinal.util.Configuration;
import org.springframework.stereotype.Service;
import com.leyana.oopfinalfinal.util.Logging;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import static com.leyana.oopfinalfinal.util.Services.initializeTicketingSystem;

/**
 * This service class handles configuration management, simulation execution, and log management.
 */
@Service
public class ConfigurationService {
    private static final String CONFIG_FILE = "config.json";
    private static final String LOG_FILE = "logs/ticketing_system.log";
    private final List<String> logMessages = new ArrayList<>();
    private AtomicBoolean isReadingLogs = new AtomicBoolean(false);

    /**
     * Retrieves the configuration from the specified file.
     * @return the loaded configuration object
     */
    public Configuration getConfiguration() {
        try {
            return Configuration.loadFromFile(CONFIG_FILE);
        } catch (RuntimeException e) {
            System.err.println("Error loading configuration: " + e.getMessage());
            return new Configuration();
        }
    }

    /**
     * Saves the configuration to the specified file.
     * @param configuration the configuration object to save
     */
    public void saveConfiguration(Configuration configuration) {
        try {
            configuration.saveToFile(CONFIG_FILE);
        } catch (RuntimeException e) {
            System.err.println("Error saving configuration: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Runs the ticketing system simulation based on the given configuration.
     * @param configuration the configuration object
     */
    public void runConfiguration(Configuration configuration) {
        try {
            Logging.log("INFO", "Starting ticketing system simulation...");
            initializeTicketingSystem(configuration);

            // Start real-time log reading in a separate thread
            isReadingLogs.set(true);
            readLogsRealTime();  // Start reading logs from the file
        } catch (Exception e) {
            Logging.log("ERROR", "Simulation failed: " + e.getMessage());
            throw new RuntimeException("Simulation failed", e);
        }
    }

    /**
     * Starts a thread to read logs from the log file in real-time.
     */
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

    /**
     * Stops the real-time log reading process.
     */
    public void stopReadingLogs() {
        isReadingLogs.set(false);
    }

    /**
     * Checks if the real-time log reading is currently active.
     * @return true if log reading is active, false otherwise
     */
    public boolean isLogReadingActive() {
        return isReadingLogs.get();
    }

    /**
     * Fetches the current log messages and clears the internal log list.
     * @return a list of log messages
     */
    public List<String> readLogs() {
        synchronized (logMessages) {
            List<String> logsToReturn = new ArrayList<>(logMessages);
            logMessages.clear();  // Clear logs after sending them to avoid duplicate data
            return logsToReturn;
        }
    }


    /**
     * Clears the contents of the log file.
     * @throws IOException if an error occurs while clearing the file
     */
    public void clearLogFile() throws IOException {
        File logFile = new File(LOG_FILE);
        if (logFile.exists()) {
            try (FileWriter fileWriter = new FileWriter(logFile)) {
                fileWriter.write("");  // Clear the file contents
            }
        }
    }
}

