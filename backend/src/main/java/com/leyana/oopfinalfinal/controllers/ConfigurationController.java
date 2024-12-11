package com.leyana.oopfinalfinal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leyana.oopfinalfinal.services.ConfigurationService;
import com.leyana.oopfinalfinal.util.Configuration;
import java.util.List;

@RestController
@RequestMapping("/api/configuration")
@CrossOrigin(origins = "*")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    // Endpoint to get configuration
    @GetMapping
    public Configuration getConfiguration() {
        System.out.println("Fetching configuration...");
        return configurationService.getConfiguration();
    }

    // Endpoint to save configuration
    @PostMapping
    public String saveConfiguration(@RequestBody Configuration configuration) {
        System.out.println("Saving configuration: " + configuration);
        configurationService.saveConfiguration(configuration);
        return "Configuration saved successfully!";
    }

    // Endpoint to run the configuration simulation
    @PostMapping("/run")
    public ResponseEntity<String> runConfiguration(@RequestBody Configuration configuration) {
        try {
            //clearing the log file
            configurationService.clearLogFile();
            System.out.println("Running configuration simulation: " + configuration);
            configurationService.runConfiguration(configuration);
            return ResponseEntity.ok("Simulation started successfully!");
        } catch (Exception e) {
            System.err.println("Error running simulation: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to start simulation.");
        }
    }

    // Endpoint to stop log reading
    @PostMapping("/stop")
    public ResponseEntity<String> stopLogReading() {
        try {
            configurationService.stopReadingLogs();
            return ResponseEntity.ok("Log reading stopped successfully!");
        } catch (Exception e) {
            System.err.println("Error stopping log reading: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to stop log reading.");
        }
    }

    // Endpoint to check if log reading is active
    @GetMapping("/is-log-reading-active")
    public boolean isLogReadingActive() {
        return configurationService.isLogReadingActive();
    }

    // Polling endpoint to stream log messages
    @GetMapping("/stream-logs")
    public ResponseEntity<List<String>> streamLogs() {
        List<String> logs = configurationService.readLogs();
        return ResponseEntity.ok(logs);
    }
}
