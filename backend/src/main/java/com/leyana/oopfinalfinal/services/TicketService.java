package com.leyana.oopfinalfinal.services;
import com.leyana.oopfinalfinal.objects.Ticket;
import com.leyana.oopfinalfinal.objects.TicketPool;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * This class provides services related to managing tickets in the ticketing system.
 * @Service annotation indicates this class is a Spring service bean.
 */
@Service
public class TicketService {
    private TicketPool ticketPool;


    public List<Ticket> getAllTickets() {
        // Create an unmodifiable copy of the ticket list to avoid modification issues
        return List.copyOf(ticketPool.getTickets());
    }

    /**
     * Adds a new ticket to the ticket pool.
     * @param ticket the Ticket object to be added
     * @return the added Ticket object
     */
    public Ticket addTicket(Ticket ticket) {
        ticketPool.addTickets(ticket);
        return ticket;
    }

    /**
     * Simulates a customer purchasing a ticket from the pool.
     * @param customerId the ID of the customer attempting to buy a ticket
     * @return the purchased Ticket object (null if no ticket available)
     */
    public Ticket buyTicket(int customerId) {
        return ticketPool.buyTickets(customerId);
    }

    /**
     * Optional method to clear all tickets from the pool (e.g., for resetting the system).
     * Use this method with caution as it removes all available tickets.
     */
    public void clearTickets() {
        ticketPool.getTickets().clear();
    }
}

