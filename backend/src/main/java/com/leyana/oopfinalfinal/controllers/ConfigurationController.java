package com.leyana.oopfinalfinal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.leyana.oopfinalfinal.services.ConfigurationService;
import com.leyana.oopfinalfinal.util.Configuration;

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
//    @PostMapping("/run")
//    public String runConfiguration(@RequestBody Configuration configuration) {
//        System.out.println("Running configuration simulation: " + configuration);
//        configurationService.runConfiguration(configuration);
//        return "Simulation started successfully!";
//    }

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

}
