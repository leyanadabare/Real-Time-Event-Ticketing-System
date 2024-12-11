package com.leyana.oopfinalfinal.controllers;

import com.leyana.oopfinalfinal.objects.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.leyana.oopfinalfinal.services.TicketService;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    // Endpoint to get all tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Endpoint to add a new ticket
    @PostMapping
    public Ticket addTicket(@RequestBody Ticket ticket) {
        return ticketService.addTicket(ticket);
    }

    // Endpoint to buy a ticket (customer buys a ticket by providing customerId)
    @DeleteMapping("/{customerId}")
    public Ticket buyTicket(@PathVariable int customerId) {
        return ticketService.buyTicket(customerId);
    }
}


