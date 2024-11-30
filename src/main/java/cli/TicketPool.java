package cli;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;
import java.util.logging.Level;

public class TicketPool {
    private Queue<Ticket> tickets;
    private int maxCapacity;
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());

    public TicketPool(int maxCapacity) {
        this.tickets = new LinkedList<>();
        this.maxCapacity = maxCapacity;
    }

    public Queue<Ticket> getTickets() {
        return tickets;
    }
    public void setTickets(Queue<Ticket> tickets) {
        this.tickets = tickets;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    //Add ticket is called by Vendor when adding tickets to the ticket pool
    // Synchronized method for adding tickets
    public synchronized void addTickets(Ticket ticket) {
        while (tickets.size() >= maxCapacity) {
            try {
                logger.info("Ticket pool full, " + Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Vendor interrupted while waiting", e);
                Thread.currentThread().interrupt();
            }
        }
        tickets.add(ticket);
        logger.info(Thread.currentThread().getName() + " added a ticket. Ticket Pool size: " + tickets.size());
        notifyAll();
    }

    // Synchronized method for purchasing tickets
    public synchronized Ticket buyTickets(int customerId) {
        while (tickets.isEmpty()) {
            try {
                logger.info("Customer: " + customerId + " waiting for tickets to become available.");
                wait();  // Wait until tickets are added
            } catch (InterruptedException e) {
                logger.log(Level.SEVERE, "Customer interrupted while waiting", e);
                Thread.currentThread().interrupt();
                return null;
            }
        }
        Ticket ticket = tickets.poll();
        logger.info("Customer " + customerId + " purchased " + ticket + ". Remaining tickets: " + tickets.size());
        notifyAll();
        return ticket;
    }

}



