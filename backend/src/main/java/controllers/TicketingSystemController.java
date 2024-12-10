package controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import services.TicketingSystemService;  // Assuming you have a service to manage the system's state
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api/system")
public class TicketingSystemController {

    @Autowired
    private TicketingSystemService ticketingSystemService;

    // Endpoint to start the ticketing system
    @PostMapping("/start")
    public ResponseEntity<String> startSystem() {
        try {
            ticketingSystemService.startSystem();
            return ResponseEntity.ok("Ticketing system started successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error starting the system: " + e.getMessage());
        }
    }

    // Endpoint to stop the ticketing system
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

