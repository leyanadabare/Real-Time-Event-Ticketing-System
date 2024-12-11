package com.leyana.oopfinalfinal.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leyana.oopfinalfinal.services.LoggingService;
import java.util.List;

@RestController
public class LoggingController {

    private final LoggingService loggingService;

    // Constructor-based dependency injection
    @Autowired
    public LoggingController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    // Endpoint to fetch the logs
    @GetMapping("/api/logs")
    public List<String> getLogs() {
        return loggingService.getLogs(); // Fetch logs via LoggingService
    }
}

