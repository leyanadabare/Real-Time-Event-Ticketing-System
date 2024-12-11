package com.leyana.oopfinalfinal.services;

import com.leyana.oopfinalfinal.objects.Ticket;
import com.leyana.oopfinalfinal.objects.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    private TicketPool ticketPool;

    // Retrieve all tickets
    public List<Ticket> getAllTickets() {
        return List.copyOf(ticketPool.getTickets()); // Convert the queue to a list
    }

    // Add a ticket to the pool
    public Ticket addTicket(Ticket ticket) {
        ticketPool.addTickets(ticket);
        return ticket;
    }

    // Buy a ticket (this is where customer buys a ticket from the pool)
    public Ticket buyTicket(int customerId) {
        return ticketPool.buyTickets(customerId);
    }

    // Optional: If you want to clear the pool (e.g., reset ticketing system), you can add such methods
    public void clearTickets() {
        ticketPool.getTickets().clear();
    }
}

