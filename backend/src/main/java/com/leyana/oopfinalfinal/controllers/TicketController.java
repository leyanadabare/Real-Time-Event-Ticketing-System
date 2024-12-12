package com.leyana.oopfinalfinal.controllers;
import com.leyana.oopfinalfinal.objects.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.leyana.oopfinalfinal.services.TicketService;
import java.util.List;

/**
 * This class defines REST API endpoints for managing tickets in the ticketing system.
 * @RestController: indicates this class is a Spring REST controller
 * @RequestMapping("/api/tickets"): defines the base path for all ticket-related endpoints
 */
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    /**
     * GET endpoint to retrieve a list of all available tickets.
     * @return List of Ticket objects
     */
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    /**
     * POST endpoint to add a new ticket to the system.
     * @param ticket the Ticket object representing the new ticket
     * @return the newly added Ticket object
     */
    @PostMapping
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    /**
     * DELETE endpoint for a customer to purchase a ticket.
     * @param customerId the ID of the customer attempting to buy a ticket
     * @return the purchased Ticket object (null if no ticket available)
     */
    @DeleteMapping("/{customerId}")
    public Ticket buyTicket(@PathVariable int customerId) {
        return ticketService.buyTicket(customerId);
    }
}


