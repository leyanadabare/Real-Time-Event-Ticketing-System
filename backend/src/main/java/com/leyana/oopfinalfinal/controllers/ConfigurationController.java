package com.leyana.oopfinalfinal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leyana.oopfinalfinal.services.ConfigurationService;
import com.leyana.oopfinalfinal.util.Configuration;
import java.util.List;

/**
 * This class defines REST API endpoints for managing the ticketing system configuration and simulation.
 * @RestController: indicates this class is a Spring REST controller
 * @RequestMapping("/api"): defines the base path for all endpoints in this controller
 * @CrossOrigin(origins = "http://localhost:4200"): enables CORS for requests originating from the specified frontend URL
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    /**
     * GET endpoint to retrieve the current configuration.
     * @return ResponseEntity containing the retrieved Configuration object
     */
    @GetMapping
    public Configuration getConfiguration() {
        System.out.println("Fetching configuration...");
        return configurationService.getConfiguration();
    }

    /**
     * POST endpoint to save the provided configuration object.
     * @param configuration the Configuration object to be saved
     * @return String message indicating success or failure
     */
    @PostMapping
    public String saveConfiguration(@RequestBody Configuration configuration) {
        System.out.println("Saving configuration: " + configuration);
        configurationService.saveConfiguration(configuration);
        return "Configuration saved successfully!";
    }

    /**
     * POST endpoint to initiate the simulation based on the provided configuration.
     * Clears the log file before starting the simulation.
     * @param configuration the Configuration object for the simulation
     * @return ResponseEntity containing a success message or error details
     */
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
    /**
     * POST endpoint to stop the real-time log reading process.
     * @return ResponseEntity containing a success message or error details
     */
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

    /**
     * GET endpoint to check if the real-time log reading is currently active.
     * @return boolean indicating whether log reading is active
     */
    @GetMapping("/is-log-reading-active")
    public boolean isLogReadingActive() {
        return configurationService.isLogReadingActive();
    }

    /**
     * GET endpoint for frontend applications to retrieve logs in a streaming fashion.
     * The service clears the internal log list after fetching to avoid memory issues.
     * @return ResponseEntity containing a list of log messages
     */
    @GetMapping("/stream-logs")
    public ResponseEntity<List<String>> streamLogs() {
        List<String> logs = configurationService.readLogs();
        return ResponseEntity.ok(logs);
    }
}
