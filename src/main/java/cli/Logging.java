package cli;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {
    private static final String LOG_FILE_NAME = "ticketing_system.log";
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
