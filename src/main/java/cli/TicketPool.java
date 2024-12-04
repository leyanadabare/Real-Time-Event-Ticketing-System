package cli;

//import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Logger;

//@Component
public class TicketPool {
    private Queue<Ticket> tickets = new LinkedList<>();
    private int maxCapacity;
    private static final Logger logger = Logger.getLogger(TicketPool.class.getName());


    public TicketPool(int maxCapacity) {
        //this.tickets = new LinkedList<>();
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
    public synchronized int getSize() {
        return tickets.size();
    }
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }


    //Add ticket is called by Vendor when adding tickets to the ticket pool
    // Synchronized method for adding tickets
    public synchronized void addTickets(Ticket ticket) {
        try {
            while (tickets.size() >= maxCapacity) {
                System.out.println("Ticket pool is full. Vendor is waiting to add tickets");
                wait();
            }
            tickets.add(ticket);
            System.out.println("Vendor added a ticket: " + ticket.getTicketId() + " Ticket pool size: " + tickets.size());
            notifyAll();
        } catch (InterruptedException e) {
            System.out.println("Error while adding tickets: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }


    // Synchronized method for purchasing tickets
    public synchronized Ticket buyTickets(int customerId) {
        try {
            while (tickets.isEmpty()){
                System.out.println("Ticket pool is empty. Customer is waiting for tickets to be available");
                wait();
            }
            Ticket ticket = tickets.poll();
            System.out.println("Customer purchased ticket: " + '\n' +
                    "Ticket ID = " + ticket.getTicketId() + '\n' +
                    "Event Name: " + ticket.getEventNameName() + '\n' +
                    "Ticket Price: " + ticket.getTicketPrice() + '\n' +
                    "Remaining Tickets: " + tickets.size());
            notifyAll();
            return ticket;
        } catch (InterruptedException e){
            System.out.println("Error while buying tickets: " + e.getMessage());
            Thread.currentThread().interrupt();
            return null;
        }
    }
}







