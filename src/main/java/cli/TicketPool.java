package cli;

import java.util.LinkedList;
import java.util.Queue;
public class TicketPool {
    private Queue<Ticket> tickets;
    private int maxCapacity;

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
                System.out.println("Ticket pool full, " + Thread.currentThread().getName() + " is waiting");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace(); // If it is a CLI program
                throw new RuntimeException("Thread interrupted: " + e.getMessage()); // Throw the exception to client
            }
        }
        tickets.add(ticket);
        System.out.println(Thread.currentThread().getName() + " added a ticket. Ticket Pool size: " + tickets.size());
        notifyAll();
    }

    // Synchronized method for purchasing tickets
    public synchronized Ticket buyTickets(int customerId) {
        while (tickets.isEmpty()) {
            try {
                System.out.println("Customer: " + customerId + " waiting for tickets to become available.");
                wait();  // Wait until tickets are added
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Thread inturrupted: " + e.getMessage());
            }
        }
        Ticket ticket = tickets.poll();
        System.out.println("Customer " + customerId + " purchased " + ticket + ". Remaining tickets: " + tickets.size());
        notifyAll();
        return ticket;
    }

}



