package com.leyana.oopfinalfinal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leyana.oopfinalfinal.services.LoggingService;
import java.util.List;

/**
 * This class defines a REST API endpoint for fetching system logs.
 * @RestController: indicates this class is a Spring REST controller
 */
@RestController
public class LoggingController {

    private final LoggingService loggingService;

    // Constructor-based dependency injection
    @Autowired
    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    /**
     * GET endpoint to retrieve a list of system logs.
     * @return List of String containing log messages
     */
    @GetMapping("/api/logs")
    public List<String> getLogs() {
        return loggingService.getLogs(); // Fetch logs via LoggingService
    }
}

