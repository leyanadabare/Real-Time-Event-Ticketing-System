package com.leyana.oopfinalfinal.services;
import org.springframework.stereotype.Service;

/**
 * This class provides services for starting, stopping, and checking the status of the
 * ticketing system.
 * @Service annotation indicates this class is a Spring service bean.
 */
@Service
public class TicketingSystemService {
    private boolean systemRunning = false;

    /**
     * Starts the ticketing system.
     * @throws RuntimeException if the system is already running
     */
    public void startSystem() {
        if (!systemRunning) {
            // Logic to start the ticketing system
            // For example: Start the ticket generation and retrieval process
            systemRunning = true;
            System.out.println("Ticketing system started.");
        } else {
            throw new RuntimeException("System is already running.");
        }
    }

    /**
     * Stops the ticketing system.
     * @throws RuntimeException if the system is not already running
     */
    public void stopSystem() {
        if (systemRunning) {
            // Logic to stop the ticketing system
            // For example: Stop the ticket generation and retrieval process
            systemRunning = false;
            System.out.println("Ticketing system stopped.");
        } else {
            throw new RuntimeException("System is not running.");
        }
    }

    /**
     * Checks if the ticketing system is currently running.
     * @return true if the system is running, false otherwise
     */
    public boolean isSystemRunning() {
        return systemRunning;
    }
}
