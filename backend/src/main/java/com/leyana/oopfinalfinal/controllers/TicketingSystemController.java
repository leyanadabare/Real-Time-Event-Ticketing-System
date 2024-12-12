package com.leyana.oopfinalfinal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.leyana.oopfinalfinal.services.TicketingSystemService;  // Assuming you have a service to manage the system's state
import org.springframework.http.ResponseEntity;

/**
 * This class defines REST API endpoints for starting and stopping the ticketing system.
 * @RestController: indicates this class is a Spring REST controller
 * @RequestMapping("/api/system"): defines the base path for system-related endpoints
 */
@RestController
@RequestMapping("/api/system")
public class TicketingSystemController {

    @Autowired
    private TicketingSystemService ticketingSystemService;

    /**
     * POST endpoint to start the ticketing system.
     * @return ResponseEntity containing a success message or error details with appropriate HTTP status code.
     */
    @PostMapping("/start")
    public ResponseEntity<String> startSystem() {
        try {
            ticketingSystemService.startSystem();
            return ResponseEntity.ok("Ticketing system started successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error starting the system: " + e.getMessage());
        }
    }

    /**
     * POST endpoint to stop the ticketing system.
     * @return ResponseEntity containing a success message or error details with appropriate HTTP status code.
     */
    @PostMapping("/stop")
    public ResponseEntity<String> stopSystem() {
        try {
            ticketingSystemService.stopSystem();
            return ResponseEntity.ok("Ticketing system stopped successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error stopping the system: " + e.getMessage());
        }
    }
}

