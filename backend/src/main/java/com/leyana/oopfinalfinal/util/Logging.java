package com.leyana.oopfinalfinal.util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * This class provides static methods for logging messages to a file.
 */
public class Logging {

    /**
     * The path to the log file.
     */
    private static final String LOG_FILE_NAME = "/Users/leyanadaba/Desktop/OOPFinalFinal/ticketing_system.log";

    /**
     * The BufferedWriter instance for writing to the log file.
     */
    private static BufferedWriter writer;

    static {
        try {
            // Initialize the BufferedWriter in append mode
            writer = new BufferedWriter(new FileWriter(LOG_FILE_NAME, true));
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Logs a message to the log file with a specified level.
     * @param level   the log level (e.g., "INFO", "WARNING", "ERROR")
     * @param message the message to log
     */
    public static synchronized void log(String level, String message) {
        try {
            if (writer != null) {
                // Format log message with timestamp
                String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                writer.write(String.format("[%s] [%s] %s%n", timestamp, level, message));
                writer.flush();
            }
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Closes the BufferedWriter to release resources.
     */
    public static void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing log file: " + e.getMessage());
        }
    }
}