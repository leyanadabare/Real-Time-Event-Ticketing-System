package util;
import java.util.Scanner;
import static util.Services.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync // Enable asynchronous operations
public class Main {

    public static void main(String[] args) {
        System.out.println("...Welcome to the Ticketing System Application!...");
        Scanner scanner = new Scanner(System.in);
        Configuration config = new Configuration();

        try {
            Logging.log("INFO", "Starting the Ticketing System Application.");
            initializeTicketingSystem(loadConfiguration2(scanner, config));
            initializeTicketingSystem(config);
        } catch (ConfigurationLoadException | InputValidationException e) {
            Logging.log("ERROR", "Error occurred: " + e.getMessage());
        } finally {
            Logging.log("INFO", "Shutting down the Ticketing System application.");
            Logging.close();
        }
    }

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

    //just test
}
