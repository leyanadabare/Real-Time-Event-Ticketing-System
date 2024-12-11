//package com.leyana.oopfinalfinal.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.leyana.oopfinalfinal.services.ConfigurationService;
//import com.leyana.oopfinalfinal.util.Configuration;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.concurrent.atomic.AtomicBoolean;
//
//@RestController
//@RequestMapping("/api/configuration")
//@CrossOrigin(origins = "*")
//public class ConfigurationController {
//
//    @Autowired
//    private ConfigurationService configurationService;
//
//    // Endpoint to get configuration
//    @GetMapping
//    public Configuration getConfiguration() {
//        System.out.println("Fetching configuration...");
//        return configurationService.getConfiguration();
//    }
//
//    // Endpoint to save configuration
//    @PostMapping
//    public String saveConfiguration(@RequestBody Configuration configuration) {
//        System.out.println("Saving configuration: " + configuration);
//        configurationService.saveConfiguration(configuration);
//        return "Configuration saved successfully!";
//    }
//
//    // Endpoint to run the configuration simulation
////    @PostMapping("/run")
////    public String runConfiguration(@RequestBody Configuration configuration) {
////        System.out.println("Running configuration simulation: " + configuration);
////        configurationService.runConfiguration(configuration);
////        return "Simulation started successfully!";
////    }
//
//    @PostMapping("/run")
//    public ResponseEntity<String> runConfiguration(@RequestBody Configuration configuration) {
//        try {
//            System.out.println("Running configuration simulation: " + configuration);
//            configurationService.runConfiguration(configuration);
//            return ResponseEntity.ok("Simulation started successfully!");
//        } catch (Exception e) {
//            System.err.println("Error running simulation: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to start simulation.");
//        }
//    }
//
//
//
//    //Reading log files and sending to the frontend
//    private AtomicBoolean isReadingLogs = new AtomicBoolean(false);
//
//    @Value("${log.file.path:logs/ticketing_system.log}") // Ensure the log file path is correct
//    private String logFilePath;
//
//    // Endpoint to start streaming logs
//    @GetMapping("/logs")
//    public ResponseEntity<String> getLogs() {
//        if (isReadingLogs.get()) {
//            return ResponseEntity.ok("Logs are already being read.");
//        }
//        isReadingLogs.set(true);
//
//        StringBuilder logContent = new StringBuilder();
//        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
//            String line;
//            while (isReadingLogs.get() && (line = reader.readLine()) != null) {
//                logContent.append(line).append("\n");
//                // Simulate polling every second
//                Thread.sleep(1000);  // Adjust polling interval if necessary
//            }
//        } catch (IOException | InterruptedException e) {
//            return ResponseEntity.status(500).body("Error reading logs: " + e.getMessage());
//        } finally {
//            isReadingLogs.set(false);
//        }
//
//        return ResponseEntity.ok(logContent.toString());
//    }
//
//    // Endpoint to stop log streaming
//    @PostMapping("/logs/stop")
//    public ResponseEntity<String> stopLogs() {
//        isReadingLogs.set(false);
//        return ResponseEntity.ok("Log streaming stopped.");
//    }
//}
//
//


//worked fine ---
//package com.leyana.oopfinalfinal.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import com.leyana.oopfinalfinal.services.ConfigurationService;
//import com.leyana.oopfinalfinal.util.Configuration;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/configuration")
//@CrossOrigin(origins = "*")
//public class ConfigurationController {
//
//    @Autowired
//    private ConfigurationService configurationService;
//
//    // Endpoint to get configuration
//    @GetMapping
//    public Configuration getConfiguration() {
//        System.out.println("Fetching configuration...");
//        return configurationService.getConfiguration();
//    }
//
//    // Endpoint to save configuration
//    @PostMapping
//    public String saveConfiguration(@RequestBody Configuration configuration) {
//        System.out.println("Saving configuration: " + configuration);
//        configurationService.saveConfiguration(configuration);
//        return "Configuration saved successfully!";
//    }
//
//    // Endpoint to run the configuration simulation
//    @PostMapping("/run")
//    public ResponseEntity<String> runConfiguration(@RequestBody Configuration configuration) {
//        try {
//            System.out.println("Running configuration simulation: " + configuration);
//            configurationService.runConfiguration(configuration);
//            return ResponseEntity.ok("Simulation started successfully!");
//        } catch (Exception e) {
//            System.err.println("Error running simulation: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to start simulation.");
//        }
//    }
//
//    // Endpoint to stop log reading
//    @PostMapping("/stop")
//    public ResponseEntity<String> stopLogReading() {
//        try {
//            configurationService.stopReadingLogs();
//            return ResponseEntity.ok("Log reading stopped successfully!");
//        } catch (Exception e) {
//            System.err.println("Error stopping log reading: " + e.getMessage());
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to stop log reading.");
//        }
//    }
//
//    // Endpoint to check if log reading is active
//    @GetMapping("/is-log-reading-active")
//    public boolean isLogReadingActive() {
//        return configurationService.isLogReadingActive();
//    }
//
//    // Polling endpoint to stream log messages
//    @GetMapping("/stream-logs")
//    public ResponseEntity<List<String>> streamLogs() {
//        List<String> logs = configurationService.readLogs();
//        return ResponseEntity.ok(logs);
//    }
//
//    // Endpoint to stop reading logs
//    @PostMapping("/stop-reading-logs")
//    public ResponseEntity<String> stopReadingLogs() {
//        configurationService.stopReadingLogs();
//        return ResponseEntity.ok("Stopped reading logs.");
//    }
//
//
//
//}


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
