package com.leyana.oopfinalfinal.services;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoggingService {

    private static final String LOG_FILE_NAME = "/Users/leyanadaba/Desktop/OOPFinalFinal/ticketing_system.log";

    public List<String> getLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logs;
    }
}

