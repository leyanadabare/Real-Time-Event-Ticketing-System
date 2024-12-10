package services;
import org.springframework.stereotype.Service;

@Service
public class TicketingSystemService {

    private boolean systemRunning = false;

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

    public boolean isSystemRunning() {
        return systemRunning;
    }
}
