package com.leyana.oopfinalfinal.services;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides a service for retrieving log messages from the system log file.
 * @Service annotation indicates this class is a Spring service bean.
 */
@Service
public class LoggingService {
    private static final String LOG_FILE_NAME = "/Users/leyanadaba/Desktop/OOPFinalFinal/ticketing_system.log";

    /**
     * Retrieves all log messages from the system log file.
     * @return a list of String objects containing each log message
     */
    public List<String> getLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_NAME))) {
            String line;
            // Read each line from the log file and add it to the list
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }
}

